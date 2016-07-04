package com.rjxy.xmut.mynews.domain;

import java.util.List;

/**
 * Created by lovec on 2016/7/3.
 */
public class LatestDomain {

    /**
     * date : 20160703
     * stories : [{"title":"关于这个老司机，你想知道的可能都在这里了","ga_prefix":"070318","images":["http://pic4.zhimg.com/d2a8055fd8055384c1f663d532300cb7.jpg"],"multipic":true,"type":0,"id":8518302},{"images":["http://pic2.zhimg.com/f68aeb9751a4b44fff6b8f48dbca1e41.jpg"],"type":0,"id":8518463,"ga_prefix":"070317","title":"知乎好问题 · 经常加班至深夜，怎样保持身体健康？"},{"images":["http://pic2.zhimg.com/ff57eae977af273425222c13db06d1a5.jpg"],"type":0,"id":8519438,"ga_prefix":"070316","title":"我在谷歌面试用户体验设计师，不光要把 PPT 做好看"},{"images":["http://pic1.zhimg.com/595a3d9c1d37c6022ddae74cd6632820.jpg"],"type":0,"id":8511965,"ga_prefix":"070315","title":"虽然被称为「优生检查」，但不该孕期常规做"},{"images":["http://pic1.zhimg.com/907fab3af90128832a27596e886b6f44.jpg"],"type":0,"id":8515294,"ga_prefix":"070314","title":"「要怎样努力，才能成为很厉害的人？」"},{"images":["http://pic1.zhimg.com/449682c1877b352fdaaa5da5a799cc08.jpg"],"type":0,"id":8519015,"ga_prefix":"070313","title":"一场宣判，牵扯出女性堕胎权几代人的历史"},{"images":["http://pic4.zhimg.com/005cf7b377fcb0ff13582c75c5ed5927.jpg"],"type":0,"id":8517043,"ga_prefix":"070312","title":"大误 · 一个打脸的木盒"},{"images":["http://pic3.zhimg.com/cd017f7213e9fd29abdd7028a3c5d806.jpg"],"type":0,"id":8509901,"ga_prefix":"070311","title":"又忙又累压力好大，我以为我会瘦\u2026\u2026"},{"images":["http://pic3.zhimg.com/52717941830f8a203a6925a45699f98e.jpg"],"type":0,"id":8517683,"ga_prefix":"070310","title":"女生怎么根据脸型来挑好看的帽子？"},{"images":["http://pic3.zhimg.com/2a4d424b710844962287235cc2a648a2.jpg"],"type":0,"id":8516835,"ga_prefix":"070309","title":"遇到好几亿岁的朋友，也能判断它们的生日"},{"images":["http://pic1.zhimg.com/38915da28039b2d84a040ad780efe874.jpg"],"type":0,"id":8503671,"ga_prefix":"070308","title":"在谷歌工作了 10 年的人，分享他的工作体验"},{"images":["http://pic3.zhimg.com/77792c689931294f1e62c7013aaceb4e.jpg"],"type":0,"id":8512201,"ga_prefix":"070307","title":"这种小螃蟹没有大螯，反而像来卖萌的啦啦队员"},{"images":["http://pic4.zhimg.com/4db11142de1261501be668dbc73792f3.jpg"],"type":0,"id":8509742,"ga_prefix":"070307","title":"他们说值得买，大家就去买买买"},{"images":["http://pic4.zhimg.com/67c4034cce58aa7bff8d918ed3358c43.jpg"],"type":0,"id":8515902,"ga_prefix":"070307","title":"上个好大学 · 如何匹配未来的职业和兴趣？"},{"images":["http://pic1.zhimg.com/ffa381a18ae0e457de47e0f7ee0f40fc.jpg"],"type":0,"id":8517384,"ga_prefix":"070307","title":"读读日报 24 小时热门 TOP 5 · 2016 年全球最佳机场"},{"images":["http://pic2.zhimg.com/d96a76238896c84217fe3bfd5393939d.jpg"],"type":0,"id":8516436,"ga_prefix":"070306","title":"瞎扯 · 亏我当时临危不乱"}]
     * top_stories : [{"image":"http://pic2.zhimg.com/fa5dce0f604514a447dd3186956a4289.jpg","type":0,"id":8518463,"ga_prefix":"070317","title":"知乎好问题 · 经常加班至深夜，怎样保持身体健康？"},{"image":"http://pic2.zhimg.com/22e67b517869b593c6dd46bbcb13be6d.jpg","type":0,"id":8509901,"ga_prefix":"070311","title":"又忙又累压力好大，我以为我会瘦\u2026\u2026"},{"image":"http://pic2.zhimg.com/d2a9ce3f75aef495904556a82f22cfa1.jpg","type":0,"id":8503671,"ga_prefix":"070308","title":"在谷歌工作了 10 年的人，分享他的工作体验"},{"image":"http://pic3.zhimg.com/29e4356aab9edf0fb6f79a7d222e3f0a.jpg","type":0,"id":8517384,"ga_prefix":"070307","title":"读读日报 24 小时热门 TOP 5 · 2016 年全球最佳机场"},{"image":"http://pic2.zhimg.com/c0cdedd74cf6ff1ef9379a81a3c063e9.jpg","type":0,"id":8515902,"ga_prefix":"070307","title":"上个好大学 · 如何匹配未来的职业和兴趣？"}]
     */

    private String date;
    /**
     * title : 关于这个老司机，你想知道的可能都在这里了
     * ga_prefix : 070318
     * images : ["http://pic4.zhimg.com/d2a8055fd8055384c1f663d532300cb7.jpg"]
     * multipic : true
     * type : 0
     * id : 8518302
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic2.zhimg.com/fa5dce0f604514a447dd3186956a4289.jpg
     * type : 0
     * id : 8518463
     * ga_prefix : 070317
     * title : 知乎好问题 · 经常加班至深夜，怎样保持身体健康？
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage(int i) {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
