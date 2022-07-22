![トップ画像](https://user-images.githubusercontent.com/102135163/180381742-f09d9f15-3c28-4807-ba9d-23ca1ea98396.png)

## URL・ログイン方法

[https://e-shifty-shiftmanagement.com](https://e-shifty-shiftmanagement.com)

- ゲストログイン機能で簡単にログインできます
- "管理者"と"従業員"で権限を設定しており、ゲストログインは管理者権限となります(管理者と従業員の認可による違いは後述)
- 従業員 ID とパスワードでログインする場合
  - 管理者: ID 1 , パスワード "password"
  - 従業員: ID 2~10 の数字, パスワード "password"
- もし従業員の増減を触られた場合、従業員管理画面にあるリセットボタンを一度押していただけると初期データが再投入されるようになっております

## アプリ概要

[作成背景やハマった点などの Qiita の解説記事はこちら](https://qiita.com/tomo_hasp/items/d8c0cfad9e8246d481ba)

シフト管理作成サービス「E-Shifty」を作成しました。
ラク(Easy)に Shift を管理できることを目指し、主な機能は以下のとおりです。

- 各従業員のログイン機能
- 従業員情報の管理や新規登録ができる
- シフトを確認できる
- 来月分の休み希望を提出できる
- 管理者は提出された休み希望を元に半自動でシフトを作成できる

## 主な使用技術

#### バックエンド

- Java 17
- SpringBoot v2.7

#### フロントエンド

- React 17.0.0
- chakra ui 1.2.1

#### インフラ・DB

- docker
- AWS(EC2, S ３, Route53, ALB)
- MySQL 5.7

## 設計書

### E-R 図

<img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/1eefca3d-ad23-d4b4-a94c-2ebad5d88bb9.png" width="70%">

### URL 一覧

<img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/957fee09-3ef3-6898-89a4-24d852becc3a.png" width="60%">

### 画面遷移図(管理者)

※ゲストログイン時も管理者での想定でログイン処理を行っております

<img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/f278a039-02d8-e827-538d-6793e6242a28.png" width="70%">

### 画面遷移図(従業員)

<img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/64ec9d58-9b1f-676a-4d04-4e64edbf2eed.png" width="70%">

### [Swagger による API 仕様書](https://swagger-url.vercel.app/?url=https%3A%2F%2Fraw.githubusercontent.com%2FTomooAkamatsu%2FE-Shifty%2Fmain%2Fswagger%2Fopenapi.yaml#/)

## 使用イメージ

#### シフト確認画面と休み希望提出

- シフトを月毎に確認できる
- 各従業員が翌月の休み希望を提出可能
  
  <img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/4be78dcc-8e3b-2c01-581d-199816df989a.gif" width="60%">

#### 従業員管理

- 従業員一覧をカードで一目で確認できる
- 従業員詳細情報はモーダルから修正可能
- 新規登録や退職処理も実装
  
  <img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/d0865091-9190-4233-46b6-090e3c45d4dc.gif" width="60%">

#### シフト作成

- 土日と休み希望日には休みを入れる
- 1 日のうち早番(A)と遅番(D)に正社員を一人ずつ
- 残りの B,C をランダムに振り分ける
- 手動で調整可能かつ、作り直し機能も実装
- 提出された全従業員の休み一覧も確認できる
  
  <img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/2602113/a55cfffa-7148-2f22-61b8-c58635abf4bb.gif" width="60%">
