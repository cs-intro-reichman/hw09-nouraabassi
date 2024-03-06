import java.util.HashMap;
import java.util.Random;

public class LanguageModel {

    // The map of this model.
    // Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    
    // The window length used in this model.
    int windowLength;
    
    // The random number generator used by this model. 
	private Random randomGenerator;

    /** Constructs a language model with the given window length and a given
     *  seed value. Generating texts from this model multiple times with the 
     *  same seed value will produce the same random texts. Good for debugging. */
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }

    /** Constructs a language model with the given window length.
     * Generating texts from this model multiple times will produce
     * different random texts. Good for production. */
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }

    /** Builds a language model from the text in the given file (the corpus). */
	public void train(String fileName) {
		// Your code goes here
	}

    // Computes and sets the probabilities (p and cp fields) of all the
	// characters in the given list. */
	public void calculateProbabilities(List probs) {				
        
        ListIterator itr = probs.listIterator(0);
        int sum = 0;
        int i = 0;

        while(itr.hasNext()){

            itr.next();
            sum += probs.get(i).count;
            i++;

        }

        ListIterator itr1 = probs.listIterator(0);
        i = 0;

        while(itr1.hasNext()){

            probs.get(i).p = (double) probs.get(i).count/sum;
            itr1.next();
            i++;

        }

        ListIterator itr2 = probs.listIterator(0);
        i = 0;

        while(itr2.hasNext()){

            if(i == 0) probs.get(i).cp = probs.get(i).p;
            else {

                int j = 0;

                while(j <= i){

                    probs.get(i).cp += probs.get(j).p;
                    j++;
                }

            }

            i++;
            itr2.next();
        }


	}

    // Returns a random character from the given probabilities list.
	public char getRandomChar(List probs) {
		return 'g';
	}

    /**
	 * Generates a random text, based on the probabilities that were learned during training. 
	 * @param initialText - text to start with. If initialText's last substring of size numberOfLetters
	 * doesn't appear as a key in Map, we generate no text and return only the initial text. 
	 * @param numberOfLetters - the size of text to generate
	 * @return the generated text
	 */
	public String generate(String initialText, int textLength) {

        return " ";
    }

    /** Returns a string representing the map of this language model. */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : CharDataMap.keySet()) {
			List keyProbs = CharDataMap.get(key);
			str.append(key + " : " + keyProbs + "\n");
		}
		return str.toString();
	}

    public static void main(String[] args) {

        LanguageModel lm1 = new LanguageModel(0);
        List list1 = new List();

        list1.addFirst(' ');
        list1.addFirst('e');
        list1.update('e');
        list1.addFirst('t');
        list1.update('t');
        list1.addFirst('i'); 
        list1.addFirst('m');
        list1.update('m');
        list1.addFirst('o');
        list1.addFirst('c');

        lm1.calculateProbabilities(list1);

        System.out.println(list1.toString());





       


    }
}
