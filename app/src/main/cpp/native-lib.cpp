#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_khurram_zapkadtest_presentation_view_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_khurram_zapkadtest_util_Constants_baseUrl(
        JNIEnv* env,
        jobject /* this */) {
    std::string base_url = "https://api.github.com/";
    return env->NewStringUTF(base_url.c_str());
}