/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.basicplayer.BasicPlayerException;
/**
 *
 * @author onair
 */
public class Principal extends javax.swing.JFrame {

    private Lista list = new Lista();
    private Nodo actual = null;
    private Player players;
    private Short x = 0;
    private DefaultListModel modelo_lista = new DefaultListModel();
    private String ultimaLista = "vacio";
    private boolean cambios = false;
    protected boolean detenido = false;
    /**
     * Creates new form Principal
     */
    public Principal() {
        setTitle("MUSIC LOUD :))");
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/icono_musica.png"));
        setIconImage(icon);
        initComponents();
        setLocationRelativeTo(null);
        nombre_can.setEditable(false);
        //jSlider1.setEnabled(false);
        //jSlider2.setEnabled(false);

        lista_can.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList lista = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = lista.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        actual = list.get_cancion(index);
                        x = 0;
                        playActionPerformed(null);
                    }
                }
            }
        });

        try {
            BufferedReader tec = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\config"));
            String aux = tec.readLine();
            if (aux.equals("Si")) {
                aux = tec.readLine();
                if (!aux.equals("vacio")) {
                    cargarLista(aux);
                }
            } else {
                cargarListaInicio.setSelected(false);
            }
        } catch (Exception e) {
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if (!list.IsEmpety() && cambios) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Guardar cambios?");
                    if (opcion == JOptionPane.YES_OPTION) {
                        if (ultimaLista.equals("vacio")) {
                            ultimaLista = crearArchivoLista();
                        }
                        if (ultimaLista == null) {
                            ultimaLista = "vacio";
                        } else {
                            guardarLista(ultimaLista);
                        }
                    }
                }
                try {
                    BufferedWriter bw = new BufferedWriter(
                            new FileWriter(System.getProperty("user.dir") + "\\config"));
                    if (cargarListaInicio.isSelected()) {
                        bw.write("Si\r\n");
                        bw.write(ultimaLista + "\r\n");
                    } else {
                        bw.write("No\r\n");
                    }
                    bw.close();
                } catch (Exception e) {
                }
                System.exit(0);
            }
        });
        players = new Player(this);
    }
    
    public void cargarLista(String ruta) {
        try {
            FileInputStream fis = new FileInputStream(new File(ruta));
            BufferedReader tec = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String input[];
            tec.readLine();

            while (tec.ready()) {
                input = tec.readLine().split("<");
                System.out.println(input[0] + " , " + input[1]);
                list.insertar(input[0], input[1]);
                modelo_lista.addElement(input[0]);
            }
            ultimaLista = ruta;
            cambios = false;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error\nal cargar la lista!!!", "alerta", 1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error!!!", "alerta", 1);
        }
        lista_can.setModel(modelo_lista);
    }
    
    public void guardarLista(String dir) {
        try {
            BufferedWriter tec = new BufferedWriter(new FileWriter(dir));
            tec.write("\r\n");

            Nodo aux = list.first;
            while (aux != null) {
                tec.append(aux.nombre + "<" + aux.direccion + "\r\n");
                aux = aux.siguiente;
            }

            tec.close();
            cambios = false;
        } catch (Exception e) {
        }
    }
    
    public String crearArchivoLista() {
        String n = JOptionPane.showInputDialog("digite el nombre de la lista");
        if (n == null || n.isEmpty()) {
            return null;
        }
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = chooser.showOpenDialog(this);
        File ruta;

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            ruta = chooser.getSelectedFile();
        } else {
            return null;
        }
        File save = new File(ruta.getAbsolutePath() + "\\" + n + ".lis");
        if (save.exists()) {
            save.delete();
        }
        return save.getAbsolutePath();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lista_can = new javax.swing.JList();
        eliminar = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        play = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        detener = new javax.swing.JButton();
        nombre_can = new javax.swing.JTextField();
        tipo_reproduccion = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cargarListaInicio = new javax.swing.JCheckBoxMenuItem();
        guardar_lista = new javax.swing.JMenuItem();
        cargar_lista = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setViewportView(lista_can);

        eliminar.setText("Quitar canción actual");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        agregar.setText("Agregar canción");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/anterior.png"))); // NOI18N
        anterior.setBorderPainted(false);
        anterior.setContentAreaFilled(false);
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.png"))); // NOI18N
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/siguiente.png"))); // NOI18N
        siguiente.setToolTipText("");
        siguiente.setBorderPainted(false);
        siguiente.setContentAreaFilled(false);
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        detener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pausa.png"))); // NOI18N
        detener.setText("Detener reproducción");
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerActionPerformed(evt);
            }
        });

        nombre_can.setText("...");
        nombre_can.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombre_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_canActionPerformed(evt);
            }
        });

        tipo_reproduccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "normal", "aleatoria" }));
        tipo_reproduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_reproduccionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pngwing.com.png"))); // NOI18N
        jLabel1.setText("MUSIC LOUD");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/YdBO.gif"))); // NOI18N

        jMenu1.setText("Archivo");

        cargarListaInicio.setSelected(true);
        cargarListaInicio.setText("Cargar ultima lista al abrir");
        cargarListaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarListaInicioActionPerformed(evt);
            }
        });
        jMenu1.add(cargarListaInicio);

        guardar_lista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        guardar_lista.setText("Guardar lista");
        guardar_lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_listaActionPerformed(evt);
            }
        });
        jMenu1.add(guardar_lista);

        cargar_lista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        cargar_lista.setText("Cargar lista");
        cargar_lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargar_listaActionPerformed(evt);
            }
        });
        jMenu1.add(cargar_lista);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem7.setText("Acerca de");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("agregar cancion");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("eliminar cancion");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem6.setText("ayuda");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(detener, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                        .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addComponent(nombre_can, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(268, 268, 268))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(play)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(anterior, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tipo_reproduccion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(siguiente))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo_reproduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(anterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(play)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(siguiente))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)))
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(detener))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardar_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_listaActionPerformed
        if (list.IsEmpety()) {
            JOptionPane.showMessageDialog(null, "no hay canciones!!!", "alerta", 1);
            return;
        }
        guardarLista(crearArchivoLista());
    }//GEN-LAST:event_guardar_listaActionPerformed

    private void cargar_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargar_listaActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("archivo lis", "lis"));
        int seleccion = chooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            detenerActionPerformed(evt);
            list.clear();
            modelo_lista.clear();
            actual = list.first;

            String name = chooser.getSelectedFile().getName();
            if (name.length() < 4 || !name.substring(name.length() - 4, name.length()).equalsIgnoreCase(".lis")) {
                JOptionPane.showMessageDialog(null, "no es una lista", "alerta", 0);
                return;
            }
            cargarLista(chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_cargar_listaActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JOptionPane.showMessageDialog(null, "Proyecto final ESTRUCTURA DE DATOS");
        JOptionPane.showMessageDialog(null, "Equipo 07");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        agregarActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        eliminarActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JOptionPane.showMessageDialog(null, "verifique que el nombre de las canciones no tengan\ncaracteres especiales como tildes o apostrofos", "alerta", 1);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (list.IsEmpety()) {
            return;
        }
        int q = list.index(actual);
        if (q == -1) {
            JOptionPane.showMessageDialog(null, "ha ocurrido un\nerror inesperado!!!", "alerta", 1);
        } else {
            modelo_lista.remove(q);
            list.borrar(actual);
            detenerActionPerformed(evt);
            if (list.IsEmpety()) {
                actual = null;
                nombre_can.setText("...");
            } else {
                if (list.tam == 1) {
                    actual = list.first;
                } else {
                    if (actual.siguiente == null) {
                        actual = actual.anterior;
                    } else {
                        actual = actual.siguiente;
                    }
                }
                nombre_can.setText(actual.nombre);
            }
        }
        cambios = true;
    }//GEN-LAST:event_eliminarActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo MP3", "mp3", "mp3"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File files[] = fileChooser.getSelectedFiles();
            boolean noMp3 = false, repetidos = false;
            cambios = true;

            for (File file : files) {
                String name = file.getName();
                if (name.length() < 4 || !name.substring(name.length() - 4, name.length()).equalsIgnoreCase(".mp3")) {
                    noMp3 = true;
                    continue;
                }
                if (list.buscar(file.getName(), file.getPath())) {
                    repetidos = true;
                    continue;
                }
                list.insertar(file.getName(), file.getPath());
                System.out.println(file.getName());
                System.out.println(file.getPath());
                modelo_lista.addElement(file.getName());
                lista_can.setModel(modelo_lista);
            }
            if (noMp3) {
                JOptionPane.showMessageDialog(null, "Se encontro archivo(s) no mp3", "alerta", 0);
            }
            if (repetidos) {
                JOptionPane.showMessageDialog(null, "Se encontraron repetidos", "alerta", 0);
            }
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        if (actual == null) {
            return;
        }

        switch (tipo_reproduccion.getSelectedIndex()) {
            case 0:
            if (actual.anterior == null) {
                return;
            }
            actual = actual.anterior;
            break;

            case 1:
            if (actual.siguiente == null) {
                return;
            }
            actual = actual.siguiente;
            break;

            default:
            int index = (int) (Math.random() * list.tam);
            actual = list.get_cancion(index);
            break;
        }

        x = 0;
        playActionPerformed(evt);
    }//GEN-LAST:event_anteriorActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        detenido = true;
        if (list.IsEmpety()) {
            JOptionPane.showMessageDialog(null, "no hay canciones", "alerta", 1);
        } else {
            if (actual == null) {
                actual = list.first;
            }
            try {
                if (x == 0) {
                    players.control.open(new URL("file:///" + actual.direccion));
                    players.control.play();
                    System.out.println("se inicia");
                    nombre_can.setText(actual.nombre);
                    //jSlider1.setEnabled(true);
                    //jSlider2.setEnabled(true);
                    x = 1;
                    play.setIcon(new ImageIcon(getClass().getResource("/iconos/pausa.png")));
                } else {
                    if (x == 1) {
                        players.control.pause();
                        System.out.println("se pausa!!!");
                        x = 2;
                        play.setIcon(new ImageIcon(getClass().getResource("/iconos/play.png")));
                    } else {
                        players.control.resume();
                        System.out.println("se continua!!!");
                        x = 1;
                        play.setIcon(new ImageIcon(getClass().getResource("/iconos/pausa.png")));
                    }
                }
            } catch (BasicPlayerException ex) {
                JOptionPane.showMessageDialog(null, "error al abrir\nla cancion!!!", "alerta", 1);
                x = 0;
            } catch (MalformedURLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "error al abrir la direccion\nde la cancion!!!", "alerta", 1);
                x = 0;
            }
        }
        detenido = false;
    }//GEN-LAST:event_playActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        if (actual == null) {
            return;
        }

        switch (tipo_reproduccion.getSelectedIndex()) {
            case 0:
            if (actual.siguiente == null) {
                return;
            }
            actual = actual.siguiente;
            break;

            /*case 1:
            if (actual.anterior == null) {
                return;
            }
            actual = actual.anterior;
            break;*/

            default:
            int index = (int) (Math.random() * list.tam);
            actual = list.get_cancion(index);
            break;
        }

        x = 0;
        playActionPerformed(evt);
    }//GEN-LAST:event_siguienteActionPerformed

    private void detenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerActionPerformed
        detenido = true;
        play.setIcon(new ImageIcon(getClass().getResource("/iconos/play.png")));
        try {
            players.control.stop();
            x = 0;
            //jSlider1.setEnabled(false);
            //jSlider2.setEnabled(false);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        detenido = false;
    }//GEN-LAST:event_detenerActionPerformed

    private void tipo_reproduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_reproduccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_reproduccionActionPerformed

    private void cargarListaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarListaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargarListaInicioActionPerformed

    private void nombre_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_canActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_canActionPerformed

    protected void eventoSiguiente(){
        siguienteActionPerformed(null);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton anterior;
    private javax.swing.JCheckBoxMenuItem cargarListaInicio;
    private javax.swing.JMenuItem cargar_lista;
    private javax.swing.JButton detener;
    private javax.swing.JButton eliminar;
    private javax.swing.JMenuItem guardar_lista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lista_can;
    private javax.swing.JTextField nombre_can;
    private javax.swing.JButton play;
    private javax.swing.JButton siguiente;
    private javax.swing.JSlider slidereq;
    private javax.swing.JSlider slidereq1;
    private javax.swing.JSlider slidereq2;
    private javax.swing.JSlider slidereq3;
    private javax.swing.JSlider slidereq4;
    private javax.swing.JSlider slidereq5;
    private javax.swing.JSlider slidereq6;
    private javax.swing.JSlider slidereq7;
    private javax.swing.JSlider slidereq8;
    private javax.swing.JSlider slidereq9;
    private javax.swing.JComboBox tipo_reproduccion;
    // End of variables declaration//GEN-END:variables
}
