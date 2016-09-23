package lesson;

import java.util.HashMap;
import java.util.Map;

// ウェブサイトごとのログインパスワードを保存するクラス
//
// [問題] 
// 以下をSingletonパターンを用いて書き換えてください
// メインメソッドにおいて複数回取得したインスタンスが同一であることを確認してください
public class SingletonKeystore {

	private Map<String, String> keys;

	public SingletonKeystore() {
		keys = new HashMap<String, String>();
	}

	public void saveKey(String siteName, String password) {
		keys.put(siteName, password);
	}

	public String getKey(String siteName) {
		// キーが登録されていない場合は実行時例外
		if (!keys.containsKey(siteName)) {
			throw new RuntimeException("No key found for  : " + siteName);
		}

		return keys.get(siteName);
	}

	public static void main(String[] args) {

	}

}
