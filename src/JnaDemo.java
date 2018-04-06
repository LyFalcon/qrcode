import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class JnaDemo {
    //编写一个接口，必须继承Library，他要在加载库文件时用
    public interface CLibrary extends Library {
        CLibrary Instance = System.getProperty("os.name").contains("Windows")?(CLibrary) Native.loadLibrary("NLPIR", CLibrary.class):
                (CLibrary) Native.loadLibrary("NLPIR", CLibrary.class);

        public int NLPIR_Init(String sDataPath, int encoding,
                              String sLicenceCode);  // 初始化
        public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);  // 分词
        public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit, boolean bWeightOut);  // 抽取关键词
        /*
         * 如果为了突出本领域的主题词，也就是说不论出现几次，按照算法可能不会作为关键词的条件下，强制作为关键词出现的词
         * 可以在用户词典中添加，但词性必须定义为key,具体的示例如：新型能 key 环保概念 key
         */
        public String NLPIR_GetFileKeyWords(String sTextFile,int nMaxKeyLimit, boolean bWeightOut);  // 抽取文件中关键词
        public String NLPIR_GetNewWords(String sLine,int nMaxKeyLimit, boolean bWeightOut);  // 抽取新词
        public String NLPIR_GetFileNewWords(String sTextFile,int nMaxKeyLimit, boolean bWeightOut);  // 抽取文件中新词
        public int NLPIR_AddUserWord(String sWord);  // 添加用户词典
        public int NLPIR_DelUsrWord(String sWord);  // 删除用户词典
        public int NLPIR_ImportUserDict(String sFilename);  // 文件导入用户词库
        public double NLPIR_FileProcess(String sSourceFilename, String sResultFilename, int bPOStagged);  // 对文件分词
        public int NLPIR_SaveTheUsrDic();  // 保存用户词库
        public int NLPIR_SetPOSmap(int nPOSmap);
        public void NLPIR_Exit();
    }
    public static void main(String[] args) {
        //调用
        int i = CLibrary.Instance.NLPIR_AddUserWord("再见");

        System.out.print(i);
    }
}
