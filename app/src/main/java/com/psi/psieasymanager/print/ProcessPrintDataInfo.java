package com.psi.psieasymanager.print;

import android.text.TextUtils;
import android.util.SparseArray;
import com.psi.psieasymanager.print.bean.PrintComponentData;
import com.psi.psieasymanager.print.bean.PrintDataInfo;
import com.psi.psieasymanager.print.bean.PrintLineData;
import com.psi.psieasymanager.print.bean.PrintTask;
import com.psi.psieasymanager.print.template.PrintTemplate;
import com.psi.psieasymanager.print.template.PrintTemplateComponent;
import com.psi.psieasymanager.print.template.PrintTemplateModule;
import com.psi.psieasymanager.print.template.PrintTemplateRow;
import com.psi.psieasymanager.print.template.ValueStyle;
import com.psi.psieasymanager.utils.HanziUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 作者：${ylw} on 2017-05-09 13:50
 * PrintDataInfo -> PrintTask
 */
public class ProcessPrintDataInfo {

  /**
   * 打印数据 -> 打印任务
   *
   * @param dataInfo 数据载体
   * @param is58 58纸?
   */
  public static PrintTask processPrintDataInfo(PrintDataInfo dataInfo, boolean is58) {
    int all = is58 ? 32 : 58;
    PrintTask task = new PrintTask();
    List<PrintLineData> lineDataList = task.getLineDataList();

    if (true) { //结账单
      PrintTemplate template = new PrintTemplate();//TODO 获取结账单模板
      SparseArray<PrintTemplateModule> templateArray = template.getArray();
      PrintTemplateModule templateModule = templateArray.get(0);

      List<PrintTemplateRow> rows = templateModule.getRows();
      for (PrintTemplateRow row : rows) {
        //上方空行
        int blanknumber = row.getBlanknumber();
        for (int i = 0; i < blanknumber; i++) {
          lineDataList.add(PrintLineData.spaceLine());
        }
        //标题数据  components
        getRowData(lineDataList, row.getComponents(), null, all);
        //具体数据 components TODO
        //getRowData();

        //下方分割线
        if (row.getShowline()) {
          lineDataList.add(PrintLineData.dividerLine(is58));
        }
      }
    } else {
    }
    return task;
  }

  /**
   * 一行数据 RowData
   * dataInfo == null 标题栏数据
   *
   * @param componentList 组件list
   * @param all 打印纸的总长度（58mm：32  80mm：48)
   */
  private static void getRowData(List<PrintLineData> lineDataList, List<PrintTemplateComponent> componentList,
      PrintDataInfo dataInfo, int all) {
    int originRow = lineDataList.size();//原行的起点

    int size = componentList.size();//几列component
    int allLength = all - (size - 1) * 2; // 总的可分配长度 (留间隔2)

    //获取最大行数(以最大行数为基准)
    int maxRows =  getMaxRows(componentList, dataInfo, allLength);

    //row 所有组件数据
    List<PrintComponentData> componentDataList = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      PrintTemplateComponent component = componentList.get(i);
      int width = Integer.valueOf(component.getWidth());
      int componentLength = Math.round(width * allLength / 100);//单元占用的长度
      String content = getContentByLabel(component.getLabel(), dataInfo);
      packageComponentData(originRow, i, maxRows, componentLength, component, componentDataList, content);
    }
    //列排序
    Collections.sort(componentDataList, new Comparator<PrintComponentData>() {
      @Override public int compare(PrintComponentData lhs, PrintComponentData rhs) {
        Integer lhsColumn = lhs.getColumn();
        Integer rhsColumn = rhs.getColumn();
        return lhsColumn.compareTo(rhsColumn);
      }
    });

    SparseArray<PrintLineData> lineDataSparseArray = new SparseArray<>();//缓存行数据 分列用
    for (int i = 0; i < maxRows; i++) {
      PrintLineData lineData = new PrintLineData();
      lineDataSparseArray.put(originRow + i + 1, lineData);
      lineDataList.add(lineData);
    }
    for (PrintComponentData data : componentDataList) {
      PrintLineData lineData = lineDataSparseArray.get(data.getRow());
      lineData.getDataList().add(data);
    }
  }

  /**
   * 封装 组件数据
   *
   * @param originRow 原行起点
   * @param column 列
   * @param maxRows 最大行
   * @param componentLength 单元所占用的长度
   * @param component 组成单元list
   * @param componentDataList 所有的打印组成单元数据
   */
  private static void packageComponentData(int originRow, int column, int maxRows,
      int componentLength, PrintTemplateComponent component,
      List<PrintComponentData> componentDataList, String content) {
    //样式
    ValueStyle valueStyle = component.getValueStyle();
    String align = valueStyle.getAlign();
    String font = valueStyle.getFont();
    boolean bigFont = ValueStyle.FONT_BIG.equals(font);

    //打印正文
    StringBuilder sbContent = new StringBuilder(content);
    int row = originRow;//行
    int currentRow = 0;

    while (!TextUtils.isEmpty(sbContent)) {
      row++;
      int index = getEndIndex(sbContent.toString(), componentLength, bigFont);
      int endIndex = index > sbContent.length() ? sbContent.length() : index;
      //if (endIndex <= 0) return;
      PrintComponentData componentData = new PrintComponentData(column);
      //截取出来的内容  就是该row 内容
      String subData = sbContent.substring(0, endIndex);
      //删除已截取的内容
      sbContent.delete(0, endIndex);
      //beforeSpace afterSpace
      int beforeSpace = 0;
      int afterSpace = 0;
      //正文总长度、剩余长度(拼接空字符串)
      int byteLength = HanziUtils.getByteLength(subData, bigFont);
      int surplusLength = componentLength - byteLength;//剩余
      switch (align) {
        case ValueStyle.ALIGN_LEFT:
          afterSpace = surplusLength;
          break;
        case ValueStyle.ALIGN_CENTER:
          int average = surplusLength / 2;
          beforeSpace = average + surplusLength % 2;
          afterSpace = average;
          break;
        case ValueStyle.ALIGN_RIGHT:
          beforeSpace = surplusLength;
          break;
      }
      componentData.setBeforeSpace(beforeSpace);
      componentData.setAfterSpace(afterSpace);
      componentData.setData(subData);
      componentData.setRow(row);
      componentData.setFont(font);
      componentDataList.add(componentData);
      currentRow++;
    }

    // 拼接空ComponentData
    if (maxRows - currentRow > 0) {
      for (int i = 0; i < maxRows - currentRow; i++) {
        row++;
        PrintComponentData componentData = new PrintComponentData(column);
        componentData.setRow(row);
        componentData.setBeforeSpace(componentLength);
        componentDataList.add(componentData);
      }
    }
  }

  /**
   * 获取endIndex
   * 截取 包左不包右
   */
  private static int getEndIndex(String content, int limit, boolean bigFont) {
    char[] chars = content.toCharArray();
    int length = 0;
    int endIndex = 0;
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if ((c >= 0x4e00) && (c <= 0x9fbb)) {//是不是汉字
        length = (bigFont ? 4 : 2) + length;
      } else {
        length = (bigFont ? 2 : 1) + length;
      }
      if (length > limit) {
        return endIndex + 1;
      }
      endIndex = i;
    }
    return endIndex + 1;
  }

  /**
   * 打印正文
   */
  private static String getContentByLabel(String label, PrintDataInfo dataInfo) {
    String content = null;//打印内容

    switch (label) {
      case PrintTemplateComponent.LABEL_SHOP_LOG://TODO logo 特殊处理
        content = "Fire://";
        break;
      case PrintTemplateComponent.LABEL_SHOP_NAME://店铺名称
        content = "店铺名称";
        break;
      case PrintTemplateComponent.LABEL_BILL_NAME://票据名称
        content = "结账单";
        break;
      case PrintTemplateComponent.LABEL_QR_CODE://二维码内容
        break;
      //case PrintTemplateComponent.CUSTOM://自定义
      //  content =
    }
    return content;
  }

  /**
   * row 最大行数
   */
  private static int getMaxRows(List<PrintTemplateComponent> componentList, PrintDataInfo dataInfo,
      int allLength) {
    int maxRows = 0;
    for (int i = 0; i < componentList.size(); i++) {
      PrintTemplateComponent component = componentList.get(i);
      String label = component.getLabel();
      String content = getContentByLabel(label, dataInfo);//打印正文
      int width = Integer.valueOf(component.getWidth());
      int componentLength = Math.round(width * allLength / 100);//单元可占用的总长度
      //样式
      ValueStyle valueStyle = component.getValueStyle();
      String font = valueStyle.getFont();
      boolean bigFont = ValueStyle.FONT_BIG.equals(font);
      int byteLength = HanziUtils.getByteLength(content, bigFont);
      //正文总长度
      int needRow = (byteLength + componentLength - 1) / componentLength;
      if (needRow > maxRows) {
        maxRows = needRow;
      }
    }
    return maxRows;
  }
}
