package io.github.poa1024.ai.code.buddy.intellij.ui;

import freemarker.template.Template;
import io.github.poa1024.ai.code.buddy.conf.Configuration;
import io.github.poa1024.ai.code.buddy.model.HtmlBlockWithMargin;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Getter
public class SessionWindow {

    //language=HTML
    private final Template historyTemplate;

    private JPanel content;
    private JTextPane history;
    private JTextField terminal;
    private JScrollPane scrollPanel;

    @SneakyThrows
    public SessionWindow(Consumer<String> onEnter) {
        this.historyTemplate = Configuration.getInstance()
                .getFreemarkerConf()
                .getTemplate("ui/history.html");
        terminal.addActionListener(e -> {
            var text = terminal.getText();
            terminal.setText(null);
            onEnter.accept(text);
        });
    }

    @SneakyThrows
    public void printConversation(List<Pair<HtmlBlockWithMargin, HtmlBlockWithMargin>> conversation) {
        var conversationAsString = conversation.stream()
                .map(qa -> {
                            var list = new ArrayList<String>();
                            list.add("<b>Request:</b>" + qa.getLeft().getValue());
                            if (qa.getRight() != null) {
                                list.add("<b>Answer:</b>" + qa.getRight().getValue());
                            }
                            return list;
                        }
                )
                .flatMap(Collection::stream)
                .collect(Collectors.joining());

        var templateModel = new HashMap<String, Object>();
        templateModel.put("body", conversationAsString);

        var stringWriter = new StringWriter();
        historyTemplate.process(templateModel, stringWriter);

        history.setText(stringWriter.toString());
        SwingUtilities.invokeLater(() -> {
            var vertical = scrollPanel.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

}
