import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class MainFrame extends JFrame {
    public void initialize(){

        final Font mainFont = new Font("Delivery", Font.BOLD, 18);
        final Font dropDownFont = new Font("Delivery", Font.BOLD, 15);

        JLabel lbWelcome;

        /*Cria todos os objetos a serem utilizados */
        String[] itensStringDe = {"Selecione o item","Real", "Peso Argentino","Peso Chileno","Libra Esterlina","Dolar"};     
        String[] itensStringPara = {"Selecione o item","Real", "Peso Argentino","Peso Chileno","Libra Esterlina","Dolar"};
        /*Primeira etapa da tela, onde ficará o dropbdown e os titulos acima dele */
        JLabel lbDe = new JLabel("De:");
        JComboBox itensListDE = new JComboBox<>(itensStringDe);
        JLabel lbPara = new JLabel("De:");
        JComboBox itensListPARA = new JComboBox<>(itensStringPara);
        /*Onde será escrito os resultados */
        JPanel formPanel = new JPanel();

        lbWelcome = new JLabel();
        /*Botões do painel */
        JButton btnCalc = new JButton("Converter");
        JButton btnClear = new JButton("Limpar");


        /******************Form Panel ********************/
        lbDe.setFont(mainFont);           
        itensListDE.setSelectedItem(0);
        itensListDE.setFont(dropDownFont);
        itensListDE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox deSelecionado = (JComboBox)e.getSource();
                Integer deItem = (Integer)deSelecionado.getSelectedIndex();
                Integer paraSelecionado = itensListPARA.getSelectedIndex();
                if (paraSelecionado == deItem){
                    btnCalc.setEnabled(false);
                    lbWelcome.setText("Mesma moeda, selecione uma moeda diferente");
                }
                else{
                    btnCalc.setEnabled(true);
                    lbWelcome.setText("");
                }

        }
    });
        
        
        lbPara.setFont(mainFont);
        itensListPARA.setSelectedItem(0);
        itensListPARA.setFont(dropDownFont);
        itensListPARA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox paraSelecionado = (JComboBox)e.getSource();
                Integer paraItem = (Integer)paraSelecionado.getSelectedIndex();
                Integer deSelecionado = itensListDE.getSelectedIndex();
                if (paraItem == deSelecionado){
                    btnCalc.setEnabled(false);
                    lbWelcome.setText("Mesma moeda, selecione uma moeda diferente");
                }
                else{
                    btnCalc.setEnabled(true);
                    lbWelcome.setText("");
                }

        }
    });
        formPanel.setLayout(new GridLayout(4,1,5,5));
        formPanel.setOpaque(false);
        formPanel.add(lbDe);
        formPanel.add(itensListDE);
        formPanel.add(lbPara);
        formPanel.add(itensListPARA);

    /**************** Painel de texto ************* */

        lbWelcome.setFont(mainFont);

    /************* Butões Painel ******************/
        btnCalc.setFont(mainFont);
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                Integer deSelecionado = itensListDE.getSelectedIndex();
                Integer paraSelecionado = itensListPARA.getSelectedIndex();
                String[] itensMoedasNomes = {"0","BRL", "ARS","CLP","GBP","USD"};
                if ((deSelecionado == 0 || paraSelecionado ==0)){
                    lbWelcome.setText("Deve ser selecionado uma moeda para conversão.");
                }
                else{
                    String valorConvertido = alphaVantageAPI.ConectaAPI(itensMoedasNomes[deSelecionado],itensMoedasNomes[paraSelecionado]);
                    lbWelcome.setText(valorConvertido);
                }

                

        }
    });

        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                lbWelcome.setText("");
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,5,5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnCalc);
        buttonsPanel.add(btnClear);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(211, 211, 211));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(lbWelcome, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Conversor Master Plus");
        setSize(500,600);
        setMinimumSize(new Dimension(300,400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    }
