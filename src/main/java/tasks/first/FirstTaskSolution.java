package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class FirstTaskSolution implements FirstTask {

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {

        ArrayDeque<Integer> dequeNode = new ArrayDeque<>();
        HashSet<Integer> setNode = new HashSet<>();
        ArrayList<String> answer = new ArrayList<>();


        setNode.add(startIndex);
        dequeNode.addLast(startIndex);

        while (!dequeNode.isEmpty()){

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if((!setNode.contains(i))&(adjacencyMatrix[startIndex][i])){
                    setNode.add(i);
                    dequeNode.addLast(i);
                }
            }

            answer.add(String.valueOf(dequeNode.pollFirst()));
            if(!dequeNode.isEmpty()) {
                startIndex = dequeNode.peekFirst();
            }
        }
        return answer.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {

        String openBracket = "{([";
        String closeBracket = "})]";

        ArrayDeque<String> stringArrayDeque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            String strValue = String.valueOf(s.charAt(i));

            if(closeBracket.contains(strValue)){
                if(stringArrayDeque.isEmpty()){
                    return false;
                }else{
                    if(openBracket.indexOf(stringArrayDeque.pollLast()) != closeBracket.indexOf(strValue)){
                        return false;
                    }
                }
            }else{
                if(openBracket.contains(strValue)){
                    stringArrayDeque.addLast(strValue);
                }
            }
        }
        return stringArrayDeque.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {

        String [] pars = s.split(" ");
        String operator ="+-*/";

        ArrayDeque<Long> numberArrayDeque = new ArrayDeque<>();

        if(pars[0].equals(" ")){
            throw new IllegalArgumentException();
        }

        int i = 0;
        while (!operator.contains(pars[i])){
            numberArrayDeque.addLast(Long.parseLong(pars[i]));
            i++;
        }

        for (int j = i; j < pars.length; j++) {

            long a;
            if((!numberArrayDeque.isEmpty())&(numberArrayDeque.size()>=2)) {
                switch (pars[j]) {
                    case "+":
                        a = numberArrayDeque.pollLast() + numberArrayDeque.pollLast();
                        numberArrayDeque.addLast(a);
                        break;
                    case "-":
                        long h = numberArrayDeque.pollLast();
                        a = numberArrayDeque.pollLast() - h;
                        numberArrayDeque.addLast(a);
                        break;
                    case "*":
                        a = numberArrayDeque.pollLast() * numberArrayDeque.pollLast();
                        numberArrayDeque.addLast(a);
                        break;
                    case "/":
                        long del = numberArrayDeque.pollLast();
                        a = numberArrayDeque.pollLast() / del;
                        numberArrayDeque.addLast(a);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        return numberArrayDeque.poll();
    }
}
