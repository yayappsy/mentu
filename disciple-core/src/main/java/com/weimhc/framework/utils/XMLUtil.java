package com.weimhc.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Created by FirenzesEagle on 2016/7/7 0007. Email:liumingbo2008@gmail.com
 */
public class XMLUtil {

	/**
	 * 将微信服务器发送的Request请求中Body的XML解析为Map
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseRequestXmlToMap(HttpServletRequest request)
			throws Exception {
		// 解析结果存储在HashMap中
		Map<String, String> resultMap;
		InputStream inputStream = request.getInputStream();
		resultMap = parseInputStreamToMap(inputStream);
		return resultMap;
	}

	/**
	 * 将输入流中的XML解析为Map
	 *
	 * @param inputStream
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Map<String, String> parseInputStreamToMap(InputStream inputStream)
			throws DocumentException, IOException {
		// 解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		return map;
	}

	/**
	 * 将String类型的XML解析为Map
	 *
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXmlStringToMap(String str) throws Exception {
		Map<String, String> resultMap;
		InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		resultMap = parseInputStreamToMap(inputStream);
		return resultMap;
	}

	/**
	 * 将对象直接转换成String类型的 XML输出
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertToXml(Object obj) {
		// 创建输出流
		StringWriter sw = new StringWriter();
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			marshaller.marshal(obj, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * 将对象根据路径转换成xml文件
	 * 
	 * @param obj
	 * @param path
	 * @return
	 */
	public static void convertToXml(Object obj, String path) {
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			// 创建输出流
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			marshaller.marshal(obj, fw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将String类型的xml转换成对象
	 */
	public static Object convertXmlStrToObject(Class<?> clazz, String xmlStr) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			// 进行将Xml转成对象的核心接口
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader sr = new StringReader(xmlStr);
			xmlObject = unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * 将file类型的xml转换成对象
	 */
	public static Object convertXmlFileToObject(Class<?> clazz, String xmlPath) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			FileReader fr = null;
			try {
				fr = new FileReader(xmlPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			xmlObject = unmarshaller.unmarshal(fr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}
}
