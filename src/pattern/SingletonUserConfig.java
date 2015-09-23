package pattern;

//ユーザーの設定を保持するクラス
public class SingletonUserConfig {

	// このクラスのインスタンスへの参照を保持するstaticフィールド
	private static SingletonUserConfig instance;

	// コンストラクタをprivateにすることで他のクラスからのnewを禁止
	private SingletonUserConfig() {
	}

	// インスタンスへの参照を他のクラスから取得するためのstaticメソッド
	public static SingletonUserConfig getInstance() {
		// instanceにインスタンスが保持されていない場合は、newする
		if (instance == null) {
			instance = new SingletonUserConfig();
		}
		return instance;
	}

	private String name;
	private String password;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
