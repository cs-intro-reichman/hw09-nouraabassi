import java.util.HashMap;
import java.util.Random;
public class LanguageModel {
    HashMap<String, List> CharDataMap;
 int windowLength;
    private Random randomGenerator;
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }
    public void train(String fileName) {
        String window = "";
        char c;
        In in = new In(fileName);
        for (int i = 0; i < windowLength; i++) {
            window += in.readChar();
        }
        while (!in.isEmpty()) {
            c = in.readChar();
            List probs = CharDataMap.get(window);
            if (probs == null) {
                probs = new List();
                CharDataMap.put(window, probs);
            }

            probs.update(c);
            window += c;
            window = window.substring(1);
        }
        for (List probs : CharDataMap.values()) {
            calculateProbabilities(probs);
        }
    }

    public void calculateProbabilities(List probs) {
        int totalCharCount = 0;
        for (int i = 0; i < probs.getSize(); i++) {
            totalCharCount += probs.get(i).count;
        }

        CharData first = probs.get(0);
        double firstP = first.count / (double) totalCharCount;
        first.p = firstP;
        first.cp = firstP;

        CharData prev = first;
        CharData current;
        for (int i = 1; i < probs.getSize(); i++) {
            current = probs.get(i);
            double p = current.count / (double) totalCharCount;
            current.p = p;
            current.cp = prev.cp + p;

            prev = current;
        }
    }

    public char getRandomChar(List probs) {
        double r = randomGenerator.nextDouble();
        int listSize = probs.getSize();

        for (int i = 0; i < listSize; i++) {
            CharData currentCharData = probs.get(i);
            if (currentCharData.cp > r) {
                return currentCharData.chr;
            }
        }

        return probs.get(listSize - 1).chr;
    }

    public String generate(String initialText, int textLength) {
        if (initialText.length() < windowLength) {
            return initialText;
        }

        String window = initialText.substring(initialText.length() - windowLength);
        String generatedText = window;
        for (int i = 0; i < textLength; i++) {
            List probs = CharDataMap.get(window);
            if (probs != null) {
                char newChar = getRandomChar(probs);
                generatedText += newChar;
                window = generatedText.substring(generatedText.length() - windowLength);
            } else {
                return generatedText;
            }
        }

        return generatedText;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String key : CharDataMap.keySet()) {
            List keyProbs = CharDataMap.get(key);
            str.append(key + " : " + keyProbs + "\n");
        }
        return str.toString(); }
    public static void main(String[] args) {
       
    }
}
