package io.github.poa1024.ai.code.buddy.intellij.ui;

import io.github.poa1024.ai.code.buddy.conf.AICBContextHolder;
import io.github.poa1024.ai.code.buddy.html.HtmlHistoryPrinter;
import io.github.poa1024.ai.code.buddy.html.model.HtmlBlock;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class SessionWindow {

    //language=HTML
    private final HtmlHistoryPrinter htmlHistoryPrinter;

    private JPanel content;
    private JTextPane history;
    private JTextField terminal;
    private JScrollPane scrollPanel;

    @SneakyThrows
    public SessionWindow(Consumer<String> onEnter) {
        this.htmlHistoryPrinter = AICBContextHolder.getContext()
                .getHtmlHistoryPrinter();
        terminal.addActionListener(e -> {
            var text = terminal.getText();
            terminal.setText(null);
            onEnter.accept(text);
        });
    }

    public void printConversation(String prompt, List<HtmlBlock> conversation) {
        var conversationAsHtml = htmlHistoryPrinter.printAsString(conversation);
        history.setText(conversationAsHtml);
        PromptSupport.setPrompt(prompt, terminal);
        SwingUtilities.invokeLater(() -> {
            var vertical = scrollPanel.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }
}
