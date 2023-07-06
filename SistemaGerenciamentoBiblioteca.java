import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SistemaGerenciamentoBiblioteca extends JFrame implements ActionListener {
private JLabel label1, label2, label3, label4, label5, label6, label7;
private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
private JPanel panel;
private ArrayList<String[]> livros = new ArrayList<String[]>();
public SistemaGerenciamentoBiblioteca() {
    setTitle("Sistema de Gerenciamento de Biblioteca");
    setSize(600, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    label1 = new JLabel("ID do Livro");
    label2 = new JLabel("Título do Livro");
    label3 = new JLabel("Autor");
    label4 = new JLabel("Editora");
    label5 = new JLabel("Ano de Publicação");
    label6 = new JLabel("ISBN");
    label7 = new JLabel("Número de Cópias");

    textField1 = new JTextField(10);
    textField2 = new JTextField(20);
    textField3 = new JTextField(20);
    textField4 = new JTextField(20);
    textField5 = new JTextField(10);
    textField6 = new JTextField(20);
    textField7 = new JTextField(10);

    addButton = new JButton("Adicionar");
    viewButton = new JButton("Visualizar");
    editButton = new JButton("Editar");
    deleteButton = new JButton("Excluir");
    clearButton = new JButton("Limpar");
    exitButton = new JButton("Sair");

    addButton.addActionListener(this);
    viewButton.addActionListener(this);
    editButton.addActionListener(this);
    deleteButton.addActionListener(this);
    clearButton.addActionListener(this);
    exitButton.addActionListener(this);

    panel = new JPanel(new GridLayout(10, 2));
    panel.add(label1);
    panel.add(textField1);
    panel.add(label2);
    panel.add(textField2);
    panel.add(label3);
    panel.add(textField3);
    panel.add(label4);
    panel.add(textField4);
    panel.add(label5);
    panel.add(textField5);
    panel.add(label6);
    panel.add(textField6);
    panel.add(label7);
    panel.add(textField7);
    panel.add(addButton);
    panel.add(viewButton);
    panel.add(editButton);
    panel.add(deleteButton);
    panel.add(clearButton);
    panel.add(exitButton);

    add(panel);
    setVisible(true);
}


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                String[] livro = new String[7];
                livro[0] = textField1.getText();
                livro[1] = textField2.getText();
                livro[2] = textField3.getText();
                livro[3] = textField4.getText();
                livro[4] = textField5.getText();
                livro[5] = textField6.getText();
                livro[6] = textField7.getText();

                // Verifica se o ID do livro já existe
                if (livroJaExiste(livro[0])) {
                    throw new IllegalArgumentException("Livro já existe na lista.");
                }

                livros.add(livro);
                JOptionPane.showMessageDialog(this, "Livro adicionado com sucesso");
                limparCampos();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
    } else if (e.getSource() == viewButton) {
        String[] colunas = {"ID do Livro", "Título do Livro", "Autor", "Editora", "Ano de Publicação", "ISBN",
                "Número de Cópias"};
        Object[][] data = new Object[livros.size()][7];
        for (int i = 0; i < livros.size(); i++) {
            data[i][0] = livros.get(i)[0];
            data[i][1] = livros.get(i)[1];
            data[i][2] = livros.get(i)[2];
            data[i][3] = livros.get(i)[3];
            data[i][4] = livros.get(i)[4];
            data[i][5] = livros.get(i)[5];
            data[i][6] = livros.get(i)[6];
        }
        JTable table = new JTable(data, colunas);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Visualizar Livros");
        frame.add(scrollPane);
        frame.setSize(800, 400);
        frame.setVisible(true);
    } else if (e.getSource() == editButton) {
        String idLivro = JOptionPane.showInputDialog(this, "Digite o ID do livro para editar:");
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i)[0].equals(idLivro)) {
                String[] livro = new String[7];
                livro[0] = idLivro;
                livro[1] = textField2.getText();
                livro[2] = textField3.getText();
                livro[3] = textField4.getText();
                livro[4] = textField5.getText();
                livro[5] = textField6.getText();
                livro[6] = textField7.getText();
                livros.set(i, livro);
                JOptionPane.showMessageDialog(this, "Livro editado com sucesso");
                limparCampos();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não encontrado");
    } else if (e.getSource() == deleteButton) {
        String idLivro = JOptionPane.showInputDialog(this, "Digite o ID do livro para excluir:");
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i)[0].equals(idLivro)) {
                livros.remove(i);
                JOptionPane.showMessageDialog(this, "Livro excluído com sucesso");
                limparCampos();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não encontrado");
    } else if (e.getSource() == clearButton) {
        limparCampos();
    } else if (e.getSource() == exitButton) {
        System.exit(0);
    }
}

 private boolean livroJaExiste(String idLivro) {
        for (String[] livro : livros) {
            if (livro[0].equals(idLivro)) {
                return true;
            }
        }
        return false;
    }

private void limparCampos() {
    textField1.setText("");
    textField2.setText("");
    textField3.setText("");
    textField4.setText("");
    textField5.setText("");
    textField6.setText("");
    textField7.setText("");
}

public static void main(String[] args) {
    new SistemaGerenciamentoBiblioteca();
}
}