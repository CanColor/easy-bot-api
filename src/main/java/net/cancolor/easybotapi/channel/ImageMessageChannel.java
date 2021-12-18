package net.cancolor.easybotapi.channel;

/**
 * @author SoarDao
 * @title: addServerImageService
 * @projectName canColor
 * @description: 图片接口
 * @date 2021/12/13 22:12
 */
public interface ImageMessageChannel {

    /*
     * @author SoarDao
     * @date 2021-12-10
     * @param url 网络图片
     * @description: 自定义图片
     */
    MessageChannel addImageByUrl(String url);

    /*
     * @author SoarDao
     * @param imageId 腾讯上传图片后的id
     * @date 2021-12-10
     * @description: 自定义图片
     */
    MessageChannel addImageByImageId(String imageId);


    /*
     * @author SoarDao
     * @param imageId 本地图片路径
     * @date 2021-12-10
     * @description: 自定义图片
     */
    MessageChannel addImageByImageByFilePath(String filePath);


    /*
     * @author SoarDao
     * @date 2021-12-10
     * @param url 网络图片
     * @description: 自定义图片
     */
    MessageChannel addFlashImageByUrl(String url);

    /*
     * @author SoarDao
     * @param imageId 腾讯上传图片后的id
     * @date 2021-12-10
     * @description: 自定义图片
     */
    MessageChannel addFlashImageByImageId(String imageId);


    /*
     * @author SoarDao
     * @param imageId 本地图片路径
     * @date 2021-12-10
     * @description: 自定义图片
     */
    MessageChannel addFlashImageByImageByFilePath(String filePath);


}
