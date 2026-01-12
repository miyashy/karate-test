# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## プロジェクト概要

Spring Boot 4.0.1とKarateを使用したREST APIのサンプルプロジェクト。TodoとUserのCRUD操作を提供し、統合テストとE2Eテストの両方をKarateで実装しています。

### 技術スタック
- **Spring Boot**: 4.0.1
- **Java**: 21
- **Gradle**: 8.14
- **Build Script**: Groovy DSL
- **Testing Framework**:
  - JUnit 6 (Unit/Integration Test)
  - JUnit 5 (E2E Test - Karate互換性のため固定)
  - Karate 1.4.1

## ビルドとテスト実行コマンド

### アプリケーションの起動
```shell
./gradlew bootRun
```
- デフォルトポート: 9080
- Swagger UI: http://127.0.0.1:9080/swagger-ui.html

### テストの実行
```shell
# 単体テスト (Karateテストを除く)
./gradlew test

# 統合テスト (アプリケーションを起動してKarateテストを実行)
./gradlew integrationTest

# E2Eテスト (個別実行)
./gradlew endToEndTest

# E2Eテスト (並列実行)
./gradlew parallelEndToEndTest
```

### ビルド
```shell
./gradlew build
```

### テストレポートの確認
- Karateのテストレポート: `./build/surefire-reports/karate-summary.html`

## アーキテクチャ

### レイヤー構造

このプロジェクトは標準的なレイヤードアーキテクチャを採用しています。

- **Controller層** (`controller/`): REST APIエンドポイントの定義。リクエストの受け取りとレスポンスの返却を担当
- **Service層** (`service/`): ビジネスロジックの実装。リポジトリを介してデータアクセスを行う
- **Domain層** (`domain/`): エンティティとリポジトリインターフェースを定義
  - エンティティ: `Todo`, `User` - イミュータブルな設計で、ファクトリメソッドと更新メソッドを提供
  - リポジトリインターフェース: `TodoRepository`, `UserRepository`
- **Infrastructure層** (`infrastructure/`): リポジトリの実装。現在はインメモリのHashMapを使用

### テスト構成

プロジェクトには3種類のテスト構成があります:

1. **test** (src/test): 通常のJUnit単体テスト
2. **integrationTest** (src/integrationTest): Spring Bootアプリケーションを起動してKarateテストを実行
   - `@SpringBootTest`でランダムポートを使用
   - 各テストクラスが独立したアプリケーションインスタンスを起動
3. **endToEndTest** (src/endToEndTest): E2Eテスト用のKarateテスト
   - `RunAllTest`クラスで全テストを並列実行可能
   - `@Tag("all")`で並列実行対象を制御

### Karateテストの構造

- **Featureファイル**: `src/{integrationTest,endToEndTest}/resources/*.feature`
- **共通処理**: `common/`ディレクトリに再利用可能なfeatureファイルを配置
  - `create-user.feature`: ユーザー作成
  - `delete-user.feature`: ユーザー削除
- **テストの実行方法**:
  - 統合テストでは各コントローラーのテストクラス(`TodoControllerTest`, `UserControllerTest`)がfeatureファイルを実行
  - ポート番号はシステムプロパティ経由でfeatureファイルに渡される

### データの永続化

現在、TodoとUserのデータは`HashMap`を使用したインメモリストレージに保存されています。アプリケーションの再起動でデータは失われます。

## 開発時の注意点

- Java 21を使用 (sourceCompatibility/targetCompatibility)
- Gradleバージョン: 8.14
- Lombokを使用しているため、IDEでアノテーションプロセッサを有効にする必要があります
- Karateテストではポート番号を動的に設定するため、`karate.properties['port']`を使用

## 開発ワークフロー

### ブランチ管理

#### 新しいブランチの作成
1. **必ず最新のmasterから作成する**
   ```shell
   git checkout master
   git fetch origin
   git reset --hard origin/master
   git checkout -b feature/your-feature-name
   ```
2. ブランチ名の規則:
   - `feature/`: 新機能追加
   - `fix/`: バグ修正
   - `refactor/`: リファクタリング
   - `chore/`: 依存関係更新、設定変更
   - `upgrade/`: メジャーバージョンアップグレード

#### PRマージ後のクリーンアップ
```shell
git checkout master
git pull origin master
git branch -d <merged-branch-name>
```

### 変更管理

#### 重要な変更は計画を作成
以下のような変更は `/plan` コマンドで計画を作成してから実施する:
- メジャーバージョンアップグレード
- アーキテクチャの変更
- ビルドシステムの変更
- 複数ファイルにまたがる大きな変更

#### コミット前の確認事項
1. **変更内容の確認**
   ```shell
   git status
   git diff
   ```
2. **必要なファイルがすべて含まれているか確認**
   - Gradle Wrapperの更新時は `gradle/wrapper/` ディレクトリ全体を確認
   - 設定ファイルの変更漏れがないか確認

3. **テストの実行**
   ```shell
   ./gradlew clean build
   ./gradlew integrationTest
   # E2Eテストは別ターミナルでbootRun起動後に実行
   ./gradlew parallelEndToEndTest
   ```

### テスト戦略

#### テストの種類と実行順序
1. **ビルド**: `./gradlew clean build`
2. **統合テスト**: `./gradlew integrationTest`
   - Spring Bootアプリケーションを起動してKarateテストを実行
   - JUnit 6を使用
3. **E2Eテスト**: `./gradlew parallelEndToEndTest`
   - 別プロセスで起動したアプリケーションに対してテスト
   - JUnit 5を使用（Karate互換性のため）

#### JUnit対応方針
- **Unit/Integration Test**: Spring Boot管理のJUnitバージョンに自動追従
- **E2E Test (Karate)**: JUnit 5.xに固定
  - `build.gradle`の`configurations`で`resolutionStrategy.force`を使用
  - Karate 1.4.1がJUnit 6未対応のため

### PR作成ガイドライン

#### PR作成前のチェックリスト
- [ ] 最新のmasterからブランチを作成した
- [ ] ローカルですべてのテストが成功した
- [ ] コミットメッセージが明確
- [ ] 不要なファイルが含まれていない

#### PRの説明に含める内容
1. **概要**: 何を変更したか
2. **主な変更**: 具体的な変更内容
3. **動作確認結果**: テスト結果、実行したコマンド
4. **リスク**: 既知の問題や制限事項
5. **CI確認予定**: CIで確認すべき項目

#### コミットメッセージの規則
```
<type>: <subject>

<body>

Co-Authored-By: Claude Sonnet 4.5 <noreply@anthropic.com>
```

**Type:**
- `feat`: 新機能
- `fix`: バグ修正
- `refactor`: リファクタリング
- `chore`: 依存関係更新、ビルド設定変更
- `docs`: ドキュメント更新
- `test`: テスト追加・修正

### バージョンアップグレード

#### 段階的アップグレードの原則
- メジャーバージョンアップは1つずつ実施
- 各段階でテストを実行して動作確認
- 依存関係の互換性を事前に調査

#### Spring Bootアップグレード時の確認事項
1. **最小要件の確認**
   - Javaバージョン
   - Gradleバージョン
   - 依存ライブラリの対応状況

2. **破壊的変更の確認**
   - 公式リリースノートと移行ガイドを確認
   - 非推奨APIの使用箇所を特定

3. **テスト戦略**
   - ビルド → 統合テスト → E2Eテストの順で実行
   - 失敗時は原因を特定してから次へ進む

4. **互換性の確保**
   - サードパーティライブラリが対応しているか確認
   - 対応していない場合は代替手段を検討（一時的な削除、バージョン固定など）
