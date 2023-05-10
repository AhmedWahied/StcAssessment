package proplemSolving;

public class FirstTask {

        public static void main(String[] args) {
            for(String x : "abd(jnb)asdf,abdjnbasdf,dd(df)a(ghhh)".split(","))
                System.out.println(reversing(x));
        }

        public static String reversing(String text) {
            StringBuilder newText = new StringBuilder();
            boolean flag = false;
            StringBuilder subText = new StringBuilder();
            String subTextReversed = "";


            for (char x : text.toCharArray()) {
                if (x == '(') {
                    flag = true;
                    newText.append(x);
                    continue;
                } else if (x == ')') {
                    flag = false;
                }
                if (flag) {
                    subText.append(x);

                } else {
                    for (char c : subText.toString().toCharArray()) {
                        subTextReversed = c + subTextReversed;
                    }
                    newText.append(subTextReversed);
                    newText.append(x);
                    subTextReversed = "";
                    subText = new StringBuilder();
                }
            }

            return newText.toString();
        }

}
