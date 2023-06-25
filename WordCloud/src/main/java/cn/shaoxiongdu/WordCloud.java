package cn.shaoxiongdu;

import cn.hutool.http.useragent.Platform;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 词云图生成
 */
public class WordCloud {
    
    /**
     * 词云图生成
     * @param wordCountMap 词频统计
     * @param filePath 文件路径
     * @return
     */
    public static boolean build(Map<String, String> wordCountMap, String filePath, String fileName) {
        
        // 全平台
        List<WordFrequency> wordFrequencies = wordCountMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), Integer.parseInt(entry.getValue())))
                .collect(Collectors.toList());
        
        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);
        
        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        
        //设置图片分辨率
        Dimension dimension = new Dimension(510, 510);
        //此处的设置采用内置常量即可，生成词云对象
        com.kennycason.kumo.WordCloud wordCloud = new com.kennycason.kumo.WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font(null, Font.ITALIC, 10);
        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));
        //设置背景色
        wordCloud.setBackgroundColor(new Color(255, 255, 255));
        //设置背景图片
        wordCloud.setBackground(new CircleBackground(255));
        //设置背景图层为圆形
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
        //生成词云
        wordCloud.build(wordFrequencies);
        
        // 判断目录是否存在
        File outDir = new File(filePath);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        wordCloud.writeToFile(filePath + "/" + fileName);
        return true;
    }
}
