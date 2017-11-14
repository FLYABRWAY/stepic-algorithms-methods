import java.util.*;

class CodeHufman {

    class Entry implements Comparable {
        Character letter;
        Integer frequency;

        Entry left;
        Entry right;

        Entry(Character letter, Integer frequency) {
            this.letter = letter;
            this.frequency = frequency;
        }

        Entry(Entry left, Entry right) {
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Object o) {
            return Comparator.comparingInt((CodeHufman.Entry e) -> e.frequency).compare(this, (CodeHufman.Entry) o);
        }

        @Override
        public String toString() {
            return "Entry{" + letter + ", " + frequency + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CodeHufman.Entry entry = (CodeHufman.Entry) o;

            return (letter != null ? letter.equals(entry.letter) : entry.letter == null)
                    && (frequency != null ? frequency.equals(entry.frequency) : entry.frequency == null);
        }

        @Override
        public int hashCode() {
            int result = letter.hashCode();
            result = 31 * result + frequency.hashCode();
            return result;
        }
    }

    class HufmanTable {

        String huffmanCode = "";
        String[] codeTable = new String[128];

        Entry root;

        HufmanTable(Entry entry) {
            this.root = entry;
            createTable(entry);
        }

        private void createTable(Entry localRoot) {
            //Костыль
            if (localRoot == root && localRoot.letter != null && localRoot.isLeaf()) {
                char temp = localRoot.letter;
                int index = (int)temp;
                codeTable[index] = "1";
                return;
            }
            if(localRoot.letter != null) {
                char temp = localRoot.letter;
                int index = (int)temp;
                codeTable[index] = huffmanCode;
            }
            else {
                huffmanCode += "0";
                createTable(localRoot.left);
                huffmanCode = huffmanCode.substring(0, huffmanCode.length()-1);

                huffmanCode += "1";
                createTable(localRoot.right);
                huffmanCode = huffmanCode.substring(0, huffmanCode.length()-1);
            }
        }

        String encode(char[] letters) {
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < letters.length; i++) {
                char temp = letters[i];
                int index = (int)temp;
                result.append(codeTable[index]);
            }

            return result.toString();
        }

        String decode(String codedMessage) {
            StringBuilder result = new StringBuilder();

            Entry temp = root;
            int i = 0;
            while(i < codedMessage.length()) {
                if(temp.letter == null)
                {
                    if(codedMessage.charAt(i) == '0') {
                        temp = temp.left;
                    }
                    else if(codedMessage.charAt(i) == '1') {
                        temp = temp.right;
                    }
                    i++;
                } else {
                    result.append(temp.letter);
                    temp = root;
                }
            }
            result.append(temp.letter);	//parse the last character

            return result.toString();
        }
    }

    void run() {
        Scanner in = new Scanner(System.in);
        char[] letters = in.nextLine().toCharArray();

        Map<Character, CodeHufman.Entry> lettersToEntry = createFrequencyEntry(letters);
        Queue<Entry> queue = createQueue(lettersToEntry);
        Entry rootEntry = createRootEntry(queue);
        HufmanTable hufmanTable = new HufmanTable(rootEntry);
        String codeMessage = hufmanTable.encode(letters);

        //out
        System.out.println(lettersToEntry.size() + " " + codeMessage.length());

        List<Entry> entryList = new ArrayList<>(lettersToEntry.values());
        entryList.sort(Comparator.comparingInt((Entry e) -> e.frequency).reversed());
        for (Entry entry : entryList)
            System.out.println(entry.letter + ": " + hufmanTable.codeTable[entry.letter]);

        System.out.println(codeMessage);
    }

    private Map<Character, Entry> createFrequencyEntry(char[] letters) {
        Map<Character, CodeHufman.Entry> lettersToEntry = new HashMap<>(27);
        for (char letter : letters) {
            if (lettersToEntry.containsKey(letter)) {
                CodeHufman.Entry entry = lettersToEntry.get(letter);
                entry.frequency += 1;
            } else lettersToEntry.put(letter, new CodeHufman.Entry(letter, 1));
        }
        return lettersToEntry;
    }

    private Queue<CodeHufman.Entry> createQueue(Map<Character, Entry> lettersToEntry) {
        PriorityQueue<CodeHufman.Entry> queue = new PriorityQueue<>();
        for (Map.Entry<Character, CodeHufman.Entry> pair : lettersToEntry.entrySet()) {
            queue.add(pair.getValue());
        }
        return queue;
    }

    private Entry createRootEntry(Queue<Entry> queue) {
        while (queue.size() > 1) {
            Entry first = queue.remove();
            Entry second = queue.remove();

            Entry result = new Entry(first, second);
            result.frequency = first.frequency + second.frequency;

            queue.add(result);
        }

        return queue.remove();
    }

}