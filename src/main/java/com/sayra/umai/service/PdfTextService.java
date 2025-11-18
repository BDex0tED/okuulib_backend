package com.sayra.umai.service;

import com.sayra.umai.service.impl.PdfServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PdfTextService {
  File savePdf(MultipartFile file) throws IOException;
  List<PdfServiceImpl.ChapterData> extractFromOutline(PDDocument document, PDDocumentOutline outline) throws IOException;
  PDPage resolveDestinationPage(PDOutlineItem item, PDDocument document);
  List<PdfServiceImpl.ChapterData> extractChapters(File pdfFile) throws IOException;
  String escapeHtml(String s);
  List<String> chunkTextToHtml(String text, int maxBytes);
  String cleanText(String text);
  PDPage lookupNamedDestinationPage(String name, PDDocument document);
  List<PdfServiceImpl.ChapterData> extractByTextPatterns(File pdfFile) throws IOException;

}
