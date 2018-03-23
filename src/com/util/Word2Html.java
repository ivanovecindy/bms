package com.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.extractor.Word6Extractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by lenovo on 2016/8/17.
 */
public class Word2Html {
    public static void main(String argv[]) {
        try {
            docToHtml("e://cp.doc", "e://3.html");
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doc转换为html
    public static void docToHtml(String sourceFileName,String targetFileName) throws Exception {

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        String imagePathStr ="e:/test/";
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //设置图片的相对路径
        // wordToHtmlConverter.setPicturesManager((a, b, suggestedName, d, e) -> "image" + File.separator + suggestedName);

        wordToHtmlConverter.processDocument(wordDocument);
        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
        for (Picture pic : pics) {
            //生成图片
            pic.writeImageContent(new FileOutputStream(imagePathStr + pic.suggestFullFileName()));
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName).toURI().getPath());

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
    }

}
