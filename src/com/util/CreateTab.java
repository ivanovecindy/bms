package com.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * 根据实体类生成表
 * 
 * @comment:
 * @author: luoxinwei(luoxinwei@gstarcad.com)
 * @DATE:2015-11-15 @TIME: 下午2:09:22
 */
public class CreateTab {
	public static void main(String[] args) throws Exception {
		File uploadFile = new File("d://table.txt ");
	/*	String string = createTable(BaseData.class)
				+ createTable(District.class) + createTable(ProBaseInfor.class)
				+createTable(TargetInfor.class)+ createTable(TargetCompany.class)
				+ createTable(House.class) + createTable(Land.class)
				+ createTable(Mechanical.class) + createTable(Vehicle.class)
				+ createTable(Stock.class) + createTable(Other.class)
				+ createTable(PropertyShare.class) + createTable(GDInfor.class)
				+ createTable(Finance.class) + createTable(TransferInfor.class)
				+ createTable(TransactionInfor.class) + createTable(Attachment.class)
				+ createTable(TransType.class)+createTable(Manager.class);
		FileCopyUtils.copy(string.getBytes(), uploadFile);*/
	}

	public static String createTable(Class<?> c) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table " + "t_" + c.getSimpleName().toLowerCase()
				+ " ( \n\t");
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Type type = fields[i].getGenericType();
			FieldMeta meta = fields[i].getAnnotation(FieldMeta.class);
			if (meta != null) {
				if (i > 0) {
					sb.append(" , \n\t");
				}
				sb.append(fields[i].getName().toUpperCase());
				if (String.class.toString().equals(type.toString())) {
					sb.append("  varchar(" + meta.size() + ") ");
				}
				if (Date.class.toString().equals(type.toString())) {
					sb.append("  date ");
				}
				if (Integer.class.toString().equals(type.toString())) {
					sb.append("  int DEFAULT '0' ");
				}
				if (Float.class.toString().equals(type.toString())) {
					sb.append("  float(" + meta.size() + "," + meta.digit()
							+ ") ");
				}
				if (Double.class.toString().equals(type.toString())) {
					sb.append("  double(" + meta.size() + "," + meta.digit()
							+ ")");
				}
				if (!meta.isNull()) {
					sb.append(" NOT NULL");
				}
				if (meta.isPrimaryKey()) {
					sb.append(" PRIMARY KEY");
				}
				 sb.append(" COMMENT '").append(meta.description()).append("'");
			}
		}
		sb.append("\n)");
		// System.out.println(sb.toString()+"\n\n");
		return sb.toString() + "\n\n";
	}
	public static String createOrclTable(Class<?> c,String modular) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		String table ="t_" + modular+"_" + c.getSimpleName().toLowerCase();
		sb.append("create table " + table
				+ " ( \n\t");
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Type type = fields[i].getGenericType();
			FieldMeta meta = fields[i].getAnnotation(FieldMeta.class);
			if (meta != null) {
				if (i > 0) {
					sb.append(" , \n\t");
					sb2.append("  \n\t");
				}
				sb.append(fields[i].getName().toUpperCase());
				if (String.class.toString().equals(type.toString())) {
					sb.append("  varchar(" + meta.size() + ") ");
				}
				if (Date.class.toString().equals(type.toString())) {
					sb.append("  date ");
				}
				if (Integer.class.toString().equals(type.toString())) {
					sb.append("  int DEFAULT '0' ");
				}
				if (Float.class.toString().equals(type.toString())) {
					sb.append("  float(" + meta.size() + "," + meta.digit()
							+ ") ");
				}
				if (Double.class.toString().equals(type.toString())) {
					sb.append("  number(" + meta.size() + "," + meta.digit()
							+ ")");
				}
				 
				 sb2.append(" comment on column "+table+"."+fields[i].getName().toUpperCase()+" is '").append(meta.description()).append("';");
			}
		}
		sb.append("\n)");
		//sb2.append("\n)");
		// System.out.println(sb.toString()+"\n\n");
		return sb.toString() + " ;\n\n"+sb2.toString() + "\n\n";
	}
}
