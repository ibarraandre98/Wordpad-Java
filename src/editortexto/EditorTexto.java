package editortexto;
import java.awt.font.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

public class EditorTexto extends JFrame implements ActionListener {
    Random ran=new Random();
    JMenuBar mb;
    JMenu m, m1, m2;
    JMenuItem mn, mi, mg, ms;
    JMenuItem mcop, mcor, mpeg;
    JMenuItem ayu, faq;
    static Scanner leer = new Scanner(System.in);
    Toolkit tk = getToolkit();
    Dimension dim = tk.getScreenSize();
    JPanel panel1, panel2;
    JComboBox cb, cbt;
    JScrollPane scroll;
    Font fuente, tam, fcur;
    JTextArea area = new JTextArea(5, 10);
    JButton neg, cur, subr, copiar, pegar, cortar, colfon,collet;
    String[] vecfuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String texto;
    Color color;
    Cursor cursor;
    JLabel fondo;
    ImageIcon i1, i2, i3, i4, i5;
    Icon ic1;
    Container cont=getContentPane();
    ImageIcon icf=new ImageIcon("/imagen/icono.png");
    int a=ran.nextInt(255),b=ran.nextInt(255),c=ran.nextInt(255);
    public EditorTexto() {
        setTitle("Muski Text Editor");
        setIconImage(new ImageIcon(getClass().getResource("/imagen/icono1.png")).getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(dim.width, dim.height);
        scroll = new JScrollPane(area);
        tam = new Font("Agency FB", Font.PLAIN, 9);
        area.setFont(tam);
        setLayout(new BorderLayout());
        barrasup();
        panel1();
        add(panel1, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void barrasup() {
        mb = new JMenuBar();
        setJMenuBar(mb);
        mb.setBorder(BorderFactory.createTitledBorder("BARRA DE HERRAMIENTAS"));
        mb.setBackground(new Color(1,152,117));
        m = new JMenu("Archivo");
        mb.add(m);
        m1 = new JMenu("Edicion");
        mb.add(m1);
        mn = new JMenuItem("Nuevo");
        m.add(mn);
        mn.addActionListener(this);
        mi = new JMenuItem("Abrir");
        m.add(mi);
        mi.addActionListener(this);
        mg = new JMenuItem("Guardar");
        m.add(mg);
        mg.addActionListener(this);
        ms = new JMenuItem("Salir");
        m.add(ms);
        ms.addActionListener(this);
        mcop = new JMenuItem(new DefaultEditorKit.CopyAction());
        mcop.setText("Copiar");
        m1.add(mcop);
        mcor = new JMenuItem(new DefaultEditorKit.CutAction());
        mcor.setText("Cortar");
        m1.add(mcor);
        mpeg = new JMenuItem(new DefaultEditorKit.PasteAction());
        mpeg.setText("Pegar");
        m1.add(mpeg);
        m2 = new JMenu("Ayuda");
        mb.add(m2);
        ayu = new JMenuItem("Info");
        m2.add(ayu);
        ayu.addActionListener(this);
        faq = new JMenuItem("FAQ");
        m2.add(faq);
        faq.addActionListener(this);
        
        
    }

    public void panel1() {
        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder("FUENTE"));
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(1,152,117));
        cb = new JComboBox();
        for (String fu : vecfuentes) {
            cb.addItem(fu);
        }
        panel1.add(cb);
        cb.addActionListener(this);
        cbt = new JComboBox();
        for (int i = 9; i < 73; i++) {
            String a = String.valueOf(i);
            cbt.addItem(a);
        }
        panel1.add(cbt);
        cbt.addActionListener(this);
        neg = new JButton();
        neg.setIcon(new ImageIcon(this.getClass().getResource("/imagen/bold.png")));
        panel1.add(neg);
        neg.addActionListener(this);
        cur = new JButton();
        cur.setIcon(new ImageIcon(this.getClass().getResource("/imagen/italic.png")));
        panel1.add(cur);
        cur.addActionListener(this);
        subr = new JButton();
        subr.setIcon(new ImageIcon(this.getClass().getResource("/imagen/under.png")));
        panel1.add(subr);
        subr.addActionListener(this);
        pegar = new JButton(new DefaultEditorKit.PasteAction());
        pegar.setText("𝓟𝓮𝓰𝓪𝓻");
        pegar.setIcon(new ImageIcon(this.getClass().getResource("/imagen/paste3.png")));
        panel1.add(pegar);
        copiar = new JButton(new DefaultEditorKit.CopyAction());
        copiar.setText("𝓒𝓸𝓹𝓲𝓪𝓻");
        copiar.setIcon(new ImageIcon(this.getClass().getResource("/imagen/copy2.png")));
        panel1.add(copiar);
        cortar = new JButton(new DefaultEditorKit.CutAction());
        cortar.setText("𝓒𝓸𝓻𝓽𝓪𝓻");
        cortar.setIcon(new ImageIcon(this.getClass().getResource("/imagen/cut1.png")));
        panel1.add(cortar);
        colfon = new JButton("𝓒𝓸𝓵𝓸𝓻 𝓯𝓸𝓷𝓭𝓸");
        colfon.setIcon(new ImageIcon(this.getClass().getResource("/imagen/col.png")));
        panel1.add(colfon);
        colfon.addActionListener(this);
        collet=new JButton("𝓒𝓸𝓵𝓸𝓻 𝓵𝓮𝓽𝓻𝓪");
        collet.setIcon(new ImageIcon(this.getClass().getResource("/imagen/letc.png")));
        panel1.add(collet);
        collet.addActionListener(this);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    String abrirArchivo() {
        String aux = "";
        texto = "";
        try {
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(this);
            File abre = file.getSelectedFile();
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                while ((aux = lee.readLine()) != null) {
                    texto += aux + "\n";
                }
                lee.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        return texto;
    }

    void Exportar() {
        try {
            JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
            archivo.showSaveDialog(this);
            if (archivo.getSelectedFile() != null) {
                try (FileWriter guardado = new FileWriter(archivo.getSelectedFile() + ".txt")) {
                    guardado.write(area.getText());
                    JOptionPane.showMessageDialog(rootPane, "El archivo fue guardado con éxito en la ruta establecida");
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void panel2() {
        panel2 = new JPanel();

    }
    int b1 = 0;
    int b2 = 0;
    int b3 = 0;

    @Override
    public void actionPerformed(ActionEvent e) {

        String let = (String) cb.getSelectedItem();
        String le = (String) cbt.getSelectedItem();
        int t = Integer.parseInt(le);
        if (cb.getSelectedItem().equals(let)) {
            tam = new Font(let, Font.PLAIN, t);
            area.setFont(tam);
        }
        if (cbt.getSelectedItem().equals(le)) {
            fuente = new Font(let, Font.PLAIN, t);
            area.setFont(fuente);
        }
        if (e.getSource() == neg) {
            if (b1 == 0) {
                tam = new Font(let, Font.BOLD, t);
                area.setFont(tam);
                b1 = 1;
            } else {
                tam = new Font(let, Font.PLAIN, t);
                area.setFont(tam);
                b1 = 0;
            }
        }
        if (e.getSource() == cur) {
            if (b2 == 0) {
                tam = new Font(let, Font.ITALIC, t);
                area.setFont(tam);
                b2 = 1;
            } else {
                tam = new Font(let, Font.PLAIN, t);
                area.setFont(tam);
                b2 = 0;
            }
        }
        if (e.getSource() == subr) {
            if (b3 == 0) {
                Map attributes = tam.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                area.setFont(tam.deriveFont(attributes));
                b3 = 1;
            } else {
                tam = new Font(let, Font.PLAIN, t);
                area.setFont(tam);
                b3 = 0;
            }
        }
        if (e.getSource() == mi) {
            String archivo = abrirArchivo();
            area.setText(archivo);
        }
        if (e.getSource() == mn) {
            area.setText(null);
        }
        if (e.getSource() == ms) {
            System.exit(0);
        }
        if (e.getSource() == mg) {
            Exportar();
        }
        if (e.getSource() == colfon) {
            JColorChooser Selectorcolor = new JColorChooser();
            color = Selectorcolor.showDialog(null, "Seleccione un color de fondo", Color.WHITE);
            area.setBackground(color);
        }
        if (e.getSource() == ayu) {
            JOptionPane.showMessageDialog(rootPane, "Creado por André Ibarra Pérez", "Mostrar Info", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == faq) {
            JOptionPane.showMessageDialog(rootPane, "Dudas o aclaraciones comuniquese a (ibarra.perez.16062@itsmante.edu.mx)", "Preguntas", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource()==collet){
            JColorChooser Selectorcolor = new JColorChooser();
            color = Selectorcolor.showDialog(null, "Seleccione un color de letra", Color.WHITE);
            area.setForeground(color);
        }
    }

    public static void main(String[] args) {
        try {
            new EditorTexto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido una exception "+e,"Excepcion",JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
