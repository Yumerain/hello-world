package opcv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class FaceCheck {

	public static void main(String[] args) {
		System.load("/home/rainleaf/workspace/eclipse-jee/helloworld/lib/opcv_so/libopencv_java2413.so");
		CascadeClassifier faceDetector = new CascadeClassifier(
				FaceCheck.class.getResource("/opcvdata/haarcascades/haarcascade_frontalface_alt.xml").getPath());
		Mat image = Highgui.imread("/tmp/timg.jpeg");

		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		for (Rect rect : faceDetections.toArray()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}

		String filename = "/tmp/ouput.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);
	}

}
