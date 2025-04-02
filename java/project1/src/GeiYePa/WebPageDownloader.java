package GeiYePa;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WebPageDownloader extends JFrame {
    private JTextField urlTextField;
    private JButton downloadButton;

    public WebPageDownloader() {
        super("Web Page Downloader");

        // 创建GUI组件
        urlTextField = new JTextField(40);
        downloadButton = new JButton("下载");

        // 将按钮点击事件与下载方法关联
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = urlTextField.getText();
                if (url.isEmpty()) {
                    JOptionPane.showMessageDialog(WebPageDownloader.this,
                            "请输入网页URL", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    downloadWebPage(url);
                }
            }
        });

        // 构建GUI布局
        JPanel panel = new JPanel();
        panel.add(urlTextField);
        panel.add(downloadButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void downloadWebPage(String url) {
        try {
            // 创建URL对象
            URL webpageUrl = new URL(url);

            // 打开连接
            InputStream inputStream = webpageUrl.openStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // 从URL中提取文件名
            String fileName = getFileNameFromUrl(url);

            // 创建输出流，将文件保存到本地
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer, 0, 1024)) >= 0) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // 关闭流
            bufferedInputStream.close();
            fileOutputStream.close();

            JOptionPane.showMessageDialog(this, "网页下载完成");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "下载失败: " + e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFileNameFromUrl(String url) {
        // 从URL中提取文件名
        int lastSlashIndex = url.lastIndexOf('/');
        if (lastSlashIndex >= 0 && lastSlashIndex < url.length() - 1) {
            return url.substring(lastSlashIndex + 1);
        } else {
            return "downloaded.html"; // 默认文件名
        }
    }

    public static void main(String[] args) {
        // 在事件分发线程中创建并显示GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WebPageDownloader();
            }
        });
    }
}
