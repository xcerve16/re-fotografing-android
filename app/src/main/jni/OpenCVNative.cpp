#include <OpenCVNative.h>

#include "Main.h"

Main process = Main();

void JNICALL Java_com_delaroystudios_camera_OpenCVNative_initReconstruction
  (JNIEnv *, jclass, jlong image1, jlong image2, jlong refImage, jfloat cx_f, jfloat cy_f, jfloat fx_f, jfloat fy_f, jfloat cx_c, jfloat cy_c,jfloat fx_c,jfloat  fy_c){

    cv::Mat& first_frame = *(cv::Mat*) image1;
    cv::Mat& second_frame = *(cv::Mat*) image2;
    cv::Mat& ref_image = *(cv::Mat*) refImage;

    cv::Point2f center_f = cv::Point2f(cx_f, cy_f);
    cv::Point2f focal_f = cv::Point2f(fx_f, fy_f);
    cv::Point2f center_c = cv::Point2f(cx_c, cy_c);
    cv::Point2f focal_c = cv::Point2f(fx_c, fy_c);

    process.initReconstruction(first_frame, second_frame, ref_image, center_f, focal_f, center_c, focal_c);
  }

void JNICALL Java_com_delaroystudios_camera_OpenCVNative_processReconstruction
  (JNIEnv *, jclass){
    process.processReconstruction();
  }

JNIEXPORT void JNICALL Java_com_delaroystudios_camera_OpenCVNative_nextPoint
  (JNIEnv *, jclass){
    process.nextPoint();
  }

JNIEXPORT void JNICALL Java_com_delaroystudios_camera_OpenCVNative_registrationPoints
  (JNIEnv *, jclass, jdouble x, jdouble y, jlong out){
     cv::Mat& out_mat = *(cv::Mat*) out;
     process.registrationPoints(x, y, out_mat);
  }

JNIEXPORT void JNICALL Java_com_delaroystudios_camera_OpenCVNative_initNavigation
  (JNIEnv *, jclass){
     process.initNavigation();
  }


jint JNICALL Java_com_delaroystudios_camera_OpenCVNative_processNavigation
  (JNIEnv *, jclass, jlong currentFrame, jint count_frames){
    cv::Mat& current_frame = *(cv::Mat*) currentFrame;
    return (jint) process.processNavigation(current_frame, count_frames);
  }