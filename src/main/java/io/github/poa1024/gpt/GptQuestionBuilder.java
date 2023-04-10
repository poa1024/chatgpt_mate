package io.github.poa1024.gpt;

import java.util.List;

public class GptQuestionBuilder {

    public String askForDetailedExplanation(String code) {
        return "Explain the code. Do not change the code, just add explanatory comments. " +
                "Your response should contain this code with your explanatory comments. " +
                "Code starts here: " + code;
    }

    public String askForShortExplanation(String code) {
        return "Give me a short explanation of what's happening in the code. " +
                "Try to be concise. 3-4 sentences." +
                "Wrap your answer as a comment block." +
                "Comment should be in the style of the provided code." +
                "Do not return anything except the comment block." +
                "Your answer should not contain the code itself. Just the explanation." +
                "Every new sentence should start with a new line." +
                "Code starts here: " + code;
    }

    public String askToGenerateCode(String request, String context) {
        return "Generate code based on provided request. " +
                baseCodeGenerationRequest(request, context);
    }

    public String askToGenerateCode(
            String request,
            String context,
            List<String> history
    ) {
        return "Change previously generated code by the new request. " +
                "Conversation that we had previously: \n'''\n" + historyToString(history) + "\n```\n" +
                baseCodeGenerationRequest(request, context);
    }

    private static String baseCodeGenerationRequest(String request, String context) {
        return "Your response should contain code only. " +
                "Consider that the code will the part of this context: \n```" + context + "\n```\n" +
                "But your response should have only the code generated by you. Do not return the context." +
                "Do not surround your response with quotes, just the code. " +
                "Request starts here: " + request;
    }

    private static CharSequence historyToString(List<String> history) {
        var res = new StringBuilder();

        for (String s : history) {
            res.append(s);
            res.append("\n");
        }

        return res;
    }

}
