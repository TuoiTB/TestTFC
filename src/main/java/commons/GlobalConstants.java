package commons;

import java.io.File;

public class GlobalConstants {
	public static final long LONG_TIMEOUT = 20;
	public static final long SHORT_TIMEOUT = 5;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String JAVA_VERSION = System.getProperty("java.version");
}
