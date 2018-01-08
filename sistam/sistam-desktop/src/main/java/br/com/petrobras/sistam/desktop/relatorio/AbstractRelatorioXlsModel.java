package br.com.petrobras.sistam.desktop.relatorio;

import br.com.petrobras.fcorp.common.exception.BusinessException;
import br.com.petrobras.fcorp.swing.base.BasePresentationModel;
import br.com.petrobras.sistam.common.business.SistamService;
import br.com.petrobras.sistam.common.util.FileUtil;
import br.com.petrobras.sistam.desktop.util.DesktopUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRelatorioXlsModel extends BasePresentationModel<SistamService> {

    private Logger logger = LoggerFactory.getLogger(AbstractRelatorioXlsModel.class);
    private static final String FONT_NAME = "Arial";
    private static final short FONT_SIZE = 200;
    // (begin) COLORS
    protected static final short COLOR_LIGHT_YELLOW = HSSFColor.LIGHT_YELLOW.index;
    protected static final short COLOR_GREY_10_PERCENT = HSSFColor.GREY_50_PERCENT.index; //Necessário chamar o addColorGrey10Percent(workbook); para sobrescrever a cor GREY 50%
    protected static final short COLOR_GREY_25_PERCENT = HSSFColor.GREY_25_PERCENT.index;
    protected static final short COLOR_WHITE = HSSFColor.WHITE.index;
    protected static final short COLOR_BLACK = HSSFColor.BLACK.index;
    protected static final short COLOR_DARK_BLUE = HSSFColor.DARK_BLUE.index;
    protected static final short COLOR_LIGHT_GREEN = HSSFColor.LIGHT_GREEN.index;
    protected static final short COLOR_RED = HSSFColor.RED.index;
    // (end) COLORS
    private short cellBackgroundColor = COLOR_WHITE;
    private short cellFontColor = COLOR_BLACK;
    private short cellFontBold = HSSFFont.BOLDWEIGHT_NORMAL;
    private HSSFFont cellFont;

    protected abstract String getNomeArquivoParaXls();

    protected abstract void gerarConteudoXls(HSSFSheet sheet) throws IOException;

    protected void addColorGrey10Percent(HSSFWorkbook workbook){
        HSSFPalette palette = workbook.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.GREY_50_PERCENT.index,
                (byte) 217,
                (byte) 217,
                (byte) 217);
    }
    
    public void setCellBackgroundColor(short cellBackgroundColor) {
        this.cellBackgroundColor = cellBackgroundColor;
    }

    public void setCellFontColor(short cellFontColor) {
        this.cellFontColor = cellFontColor;
    }

    public void setCellFontBold(boolean cellFontBold) {
        this.cellFontBold = cellFontBold ? HSSFFont.BOLDWEIGHT_BOLD : HSSFFont.BOLDWEIGHT_NORMAL;
    }
    
    private File exportar(String sheetName) {
        File file = null;
        try {
            file = FileUtil.createFileOnTemp(getNomeArquivoParaXls(), ".xls");
            FileOutputStream fos = new FileOutputStream(file, true);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(sheetName);

            this.cellFont = workbook.createFont();
            this.cellFont.setFontName(FONT_NAME);
            this.cellFont.setFontHeight(FONT_SIZE);
            this.cellFont.setColor(COLOR_BLACK);
            
            setCellBackgroundColor(COLOR_WHITE);
            setCellFontColor(COLOR_BLACK);
            setCellFontBold(false);

            gerarConteudoXls(sheet);

            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException(ex.getMessage(), ex);
        }
        return file;
    }

    protected void visualizarArquivo(String sheetName) {
        File file = exportar(sheetName);
        DesktopUtil.visualizarArquivo(DesktopUtil.transformarBytes(file), file.getName());
    }

    protected HSSFCell criarTitulo(HSSFRow row, int column, String value) {
        HSSFSheet sheet = row.getSheet();
        HSSFWorkbook workbook = sheet.getWorkbook();

        HSSFFont font = workbook.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeight((short) 240);
        font.setColor(COLOR_BLACK);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        HSSFCell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    protected HSSFCell criarCelula(HSSFRow row, int column, String value, Integer width, Short align, boolean borderTop, boolean borderBottom, boolean borderLeft, boolean borderRight) {
        HSSFSheet sheet = row.getSheet();
        if (width != null) {
            sheet.setColumnWidth(column, width);
        }
        HSSFWorkbook workbook = sheet.getWorkbook();
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        if (cellFontColor != COLOR_BLACK || cellFontBold != HSSFFont.BOLDWEIGHT_NORMAL) {
            HSSFFont font = workbook.createFont();
            font.setFontName(FONT_NAME);
            font.setFontHeight(FONT_SIZE);
            font.setColor(cellFontColor);
            font.setBoldweight(cellFontBold);
            cellStyle.setFont(font);
        } else {
            cellStyle.setFont(this.cellFont);
        }
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setFillForegroundColor(cellBackgroundColor);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        HSSFCellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setFillForegroundColor(cellBackgroundColor);
        
        row.setRowStyle(rowStyle);

        if (align != null) {
            cellStyle.setAlignment(align);
        }
        if (borderTop) {
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        }
        if (borderBottom) {
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        }
        if (borderLeft) {
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        }
        if (borderRight) {
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        }

        HSSFCell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    protected HSSFCell criarCelula(HSSFRow row, int column, String value, boolean borderTop, boolean borderBottom, boolean borderLeft, boolean borderRight) {
        return criarCelula(row, column, value, null, null, borderTop, borderBottom, borderLeft, borderRight);
    }
    
    protected HSSFCell criarCelulaCentralizado(HSSFRow row, int column, String value, boolean borderTop, boolean borderBottom, boolean borderLeft, boolean borderRight) {
        return criarCelula(row, column, value, null, CellStyle.ALIGN_CENTER, borderTop, borderBottom, borderLeft, borderRight);
    }

    protected HSSFCell criarCelulaSemBorda(HSSFRow row, int column, String value) {
        return criarCelula(row, column, value, null, null, false, false, false, false);
    }

    protected HSSFCell criarCelulaSemBordaCentralizado(HSSFRow row, int column, String value) {
        return criarCelula(row, column, value, null, CellStyle.ALIGN_CENTER, false, false, false, false);
    }
}
