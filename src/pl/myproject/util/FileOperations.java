package pl.myproject.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileOperations {

	public static void saveFile(HttpServletRequest request, String servletDir, String dir)
			throws IOException, ServletException {
		// String appPath = request.getServletContext().getRealPath("");
		String savePath = servletDir + File.separator + dir;// appPath +
															// File.separator +
															// SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		for (Part part : request.getParts()) {
			if (part.getContentType() != null) {
				String fileName = extractFileName(part);
				fileName = new File(fileName).getName();
				part.write(savePath + File.separator + fileName);
			}
		}

	}

	public static List<String> listFiles(HttpServletRequest request) throws IOException, ServletException {
		List<String> files = new ArrayList<>();
		for (Part part : request.getParts()) {
			if (part.getContentType() != null) {
				String fileName = extractFileName(part);
				files.add(fileName);

			}
		}
		return files;
	}

	public static byte[] zipFiles(File directory, String[] files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte[] bytes = new byte[2048];
		for (String fileName : files) {
			inputStreamOperations(fileName, directory, zos, bytes);
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();
		return baos.toByteArray();
	}

	private static void inputStreamOperations(String fileName, File directory, ZipOutputStream zos, byte[] bytes)
			throws IOException {
		FileInputStream fis = new FileInputStream(directory.getPath() + File.separator + fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		zos.putNextEntry(new ZipEntry(fileName));
		int bytesRead;
		while ((bytesRead = bis.read(bytes)) != -1) {
			zos.write(bytes, 0, bytesRead);
		}
		zos.closeEntry();
		bis.close();
		fis.close();
	}

	private static String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	public static void downloadFile(HttpServletResponse response, String servletDir, String dir)
			throws IOException, ServletException {
		String path = servletDir + File.separator + dir;
		File fileDir = new File(path);
		String[] files = fileDir.list();
		if (files != null && files.length > 0) {
			byte[] zip = FileOperations.zipFiles(fileDir, files);
			ServletOutputStream sos = response.getOutputStream();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=DATA.ZIP");
			sos.write(zip);
			sos.flush();
		}
	}

}
