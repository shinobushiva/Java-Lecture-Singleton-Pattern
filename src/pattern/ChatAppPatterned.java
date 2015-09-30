package pattern;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatAppPatterned {

	private JFrame frame;
	private JTextField inputField;
	private JTextArea historyArea;
	private JButton sendButton;

	private SingletonUserConfig config;

	public ChatAppPatterned(SingletonUserConfig config) {

		// 引数として受け取ったUserConfigのインスタンスを保持
		this.config = config;

		frame = new JFrame(getClass().getName());
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		inputField = new JTextField();
		inputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendText();
				}
			}
		});
		historyArea = new JTextArea();
		historyArea.setEditable(false);
		sendButton = new JButton("Send");

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendText();
			}
		});
		// [参考] 上と同じことを行っているがJava8で取り入れられたラムダを使って記述するこのように簡潔になる
		// sendButton.addActionListener(e -> sendText());

		frame.add(inputField, BorderLayout.NORTH);
		frame.add(new JScrollPane(historyArea), BorderLayout.CENTER);
		frame.add(sendButton, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private void sendText() {
		String txt = historyArea.getText();
		if (!inputField.getText().isEmpty()) {

			if (":config".equals(inputField.getText())) {

				// コンフィグを取得 - シングルトンであるため常に同じインスタンスしか取得できない
				// 　つまり不用意にインスタンスを生成することを避けられる
				SingletonUserConfig config = SingletonUserConfig.getInstance();

				// :config と入力された場合は設定情報をダイアログで表示
				JDialog dialog = new JDialog();
				dialog.setTitle("User Config");
				dialog.setModal(true);

				JTextArea area = new JTextArea();
				area.setEditable(false);
				// 末尾にコメントを使って読みやすく改行しているだけ
				area.setText("" //
						+ "name: " + config.getName() + "\n" //
						+ "password: " + "********" + "\n" //
						+ "e-mail: " + config.getEmail() + "\n" //
				);
				dialog.setLocationRelativeTo(frame);
				dialog.add(area);
				dialog.pack();
				dialog.setVisible(true);

			}

			// 自分の名前をつけてチャットを更新
			txt += config.getName() + "> " + inputField.getText() + "\n";
			// ダミーでロボットがオウム返し
			txt += "Robot" + "> " + inputField.getText() + "\n";
			historyArea.setText(txt);
			inputField.setText("");

		}

	}

	public static void main(String[] args) {

		// 以下の書き方はコンパイルエラーとなる
		// SingletonUserConfig config = new SingletonUserConfig();

		// シングルトンの場合はstaticメソッドを使ってインスタンスを取得
		SingletonUserConfig config = SingletonUserConfig.getInstance();

		config.setName("Shinobu");
		config.setPassword("hogehoge");
		config.setEmail("izumi@cis.sojo-u.ac.jp");

		new ChatAppPatterned(config);
	}

}
