package edu.moral.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.moral.bean.PictureResult;
import edu.moral.service.PictureService;
import edu.moral.utils.FtpUtil;
import edu.moral.utils.IDUtils;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {

	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;

	@Value("${FTP_PORT}")
	private Integer FTP_PORT;

	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;

	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;

	@Override
	public PictureResult uploadFile(MultipartFile file) throws Exception {
		try {
			String path = savePicture(file);
			// 回显
			PictureResult result = new PictureResult(0, IMAGE_BASE_URL + path);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	private String savePicture(MultipartFile uploadFile) {
		String result = null;
		try {
			// 上传文件功能实现
			// 判断文件是否为空
			if (uploadFile.isEmpty())
				return null;
			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
					+ new SimpleDateFormat("MM").format(new Date()) + "/"
					+ new SimpleDateFormat("dd").format(new Date());

			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, filePath, newFileName,
					uploadFile.getInputStream());
			result = filePath + "/" + newFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
