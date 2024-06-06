package dev.cpini.paradigmas_lab3.ui;

import dev.cpini.paradigmas_lab3.Chatbot;
import dev.cpini.paradigmas_lab3.Flow;
import dev.cpini.paradigmas_lab3.Option;
import dev.cpini.paradigmas_lab3.ui.renderable.RenderableChatbot;
import dev.cpini.paradigmas_lab3.ui.renderable.RenderableFlow;
import dev.cpini.paradigmas_lab3.ui.renderable.RenderableOption;
import dev.cpini.paradigmas_lab3.ui.renderable.RenderableSystem;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.SystemTreeNode;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Panel de vista de administrador.
 * Muestra un árbol del sistema y permite modificarlo.
 * Permite cambiar a {@link ClientPanel} y cerrar sesión.
 */
public class AdminPanel extends UIPanel {
    private final JTree tree;

    public AdminPanel(UI ui) {
        super(ui);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int y = 0;

        //Título
        JLabel titleLabel = Utils.createSubitle(String.format("Editando %s", ui.getCurrentSystem().getName()));
        add(titleLabel, GridPlacing.create(0, y++).withInsets(0, 0, 10, 0));

        //Árbol
        DefaultMutableTreeNode treeRoot = generateTree();

        //JTree
        tree = new JTree(treeRoot);
        tree.setToggleClickCount(0);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        add(new JScrollPane(tree), GridPlacing.create(0, y++).withFill(GridPlacing.Fill.BOTH).withInsets(0, 0, 10, 0)
                .withAnchor(GridPlacing.Anchor.CENTER).withWeight(1, 1));

        //Atributos
        UIPanel attributes = new UIPanel(ui, new GridBagLayout());
        tree.addTreeSelectionListener(event -> {
            if (event.getPath().getLastPathComponent() instanceof SystemTreeNode) {
                SystemTreeNode node = (SystemTreeNode) event.getPath().getLastPathComponent();
                attributes.removeAll();
                node.showAttributes(attributes);
                attributes.revalidate();
                attributes.repaint();
            }
        });
        attributes.add(new JLabel("Seleccione un objeto para verlo/editarlo"));

        //Scroll de atributos
        JScrollPane attributesScroll = new JScrollPane(attributes);
        attributesScroll.setPreferredSize(new Dimension(562, 200)); //Tamaño hardcodeado, mis disculpas
        add(attributesScroll, GridPlacing.create(0, y++).withFill(GridPlacing.Fill.BOTH).withWeight(1, 0.5));

        //Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());

        //Probar sistema
        JButton testButton = new JButton("Probar sistema");
        testButton.addActionListener(event -> ui.showClient());
        buttonPanel.add(testButton);

        //Cerrar sesión
        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(event -> {
            ui.getCurrentSystem().systemLogout();
            ui.showMainMenu();
        });
        buttonPanel.add(logoutButton);

        add(buttonPanel, GridPlacing.create(0, y).withInsets(5, 0, 0, 0).withAnchor(GridPlacing.Anchor.CENTER));

        expandTree(treeRoot);
    }

    private DefaultMutableTreeNode generateTree() {
        DefaultMutableTreeNode root = new SystemTreeNode(new RenderableSystem(getUi().getCurrentSystem()));
        for (Chatbot chatbot : getUi().getCurrentSystem().getChatbots()) {
            DefaultMutableTreeNode chatbotNode = new SystemTreeNode(new RenderableChatbot(chatbot));
            for (Flow flow : chatbot.getFlows()) {
                DefaultMutableTreeNode flowNode = new SystemTreeNode(new RenderableFlow(flow));
                for (Option option : flow.getOptions()) {
                    DefaultMutableTreeNode optionNode = new SystemTreeNode(new RenderableOption(option));
                    flowNode.add(optionNode);
                }
                chatbotNode.add(flowNode);
            }
            root.add(chatbotNode);
        }
        return root;
    }

    private void expandTree(DefaultMutableTreeNode root) {
        root.children().asIterator().forEachRemaining(node -> expandTree(((DefaultMutableTreeNode) node)));
        tree.expandPath(new TreePath(root.getPath()));
    }
}
