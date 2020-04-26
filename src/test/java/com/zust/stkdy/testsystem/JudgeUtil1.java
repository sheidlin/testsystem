import java.io.*;

public class JudgeUtil1 {
    private String answer;
    private String standardAnswer;
    private String keyWords;
    private static final String KEY_WORDS = "C:\\Users\\dell\\Desktop\\fst\\data\\in\\KeyWords.txt";
    private static final String ANSWER = "C:\\Users\\dell\\Desktop\\fst\\data\\in\\Answer.txt";
    private static final String STANDARD_ANSWER= "C:\\Users\\dell\\Desktop\\fst\\data\\in\\StandardAnswer.txt";
    private static final String PY_URL = "C:\\Users\\dell\\Desktop\\fst\\fst.py";
    private static final String OUT= "C:\\Users\\dell\\Desktop\\fst\\data\\in\\out.txt";

    public JudgeUtil1(String answer, String standardAnswer, String  keyWords) {
        this.answer = answer;
        this.standardAnswer = standardAnswer;
        this.keyWords = keyWords;
    }

    public static void writeImagePath(JudgeUtil1 judgeAnswerUtil) {
        PrintWriter pw1 = null;
        PrintWriter pw2 = null;
        PrintWriter pw3 = null;
        try {
            pw1 = new PrintWriter(new FileWriter(new File(KEY_WORDS)));
            pw1.print(judgeAnswerUtil.getKeyWords());
            pw2 = new PrintWriter(new FileWriter(new File(ANSWER)));
            pw2.println(judgeAnswerUtil.getAnswer());
            pw3 = new PrintWriter(new FileWriter(new File(STANDARD_ANSWER)));
            pw3.println(judgeAnswerUtil.getStandardAnswer());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw1.close();
        pw2.close();
        pw3.close();
    }
    public static String readAnswer() {
        BufferedReader br;
        String answer = null;
        try {
            br = new BufferedReader(new FileReader(new File(OUT)));
            answer = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
    public static void execPy() {
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("python " + PY_URL);
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public double judge(){
        JudgeUtil1 judgeAnswerUtil=this;
        writeImagePath(judgeAnswerUtil);;
        execPy();
        String score=readAnswer();
        return Double.parseDouble(score);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStandardAnswer() {
        return standardAnswer;
    }

    public static String getPyUrl() {
        return PY_URL;
    }

    public static String getOUT() {
        return OUT;
    }

    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public static String getANSWER() {
        return ANSWER;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public String toString() {
        return "JudgeUtil{" +
                "answer='" + answer + '\'' +
                ", standardAnswer='" + standardAnswer + '\'' +
                ", keyWords='" + keyWords + '\'' +
                '}';
    }
}

