# XeTank Java

XeTank is a Java-based tank battle game implementing a Model-View-Controller architecture.

## Project Structure

```
XeTank_Java/
├── src/
│   ├── controller/             # Các thành phần điều khiển game
│   │   ├── interfaces/         # Giao diện controller
│   │   │   ├── ICollisionController.java
│   │   │   ├── IGameController.java
│   │   │   └── IInputController.java
│   │   ├── CollisionController.java
│   │   ├── GameController.java
│   │   └── KeyboardController.java
│   │
│   ├── core/                   # Hằng số và tiện ích cốt lõi
│   │   └── GameConstants.java
│   │
│   ├── model/                  # Các thành phần mô hình game
│   │   ├── base/               # Các lớp trừu tượng cơ sở
│   │   │   ├── EntityBase.java
│   │   │   └── MovableEntityBase.java
│   │   │
│   │   ├── entities/           # Thực thể trong game
│   │   │   ├── Bullet.java     # Đạn
│   │   │   ├── Grass.java      # Cỏ
│   │   │   ├── ItemEntity.java # Vật phẩm
│   │   │   ├── Player.java     # Người chơi
│   │   │   ├── Wall.java       # Tường
│   │   │   └── Water.java      # Nước
│   │   │
│   │   ├── interfaces/         # Giao diện model
│   │   │   ├── ICollidable.java
│   │   │   ├── IEntity.java
│   │   │   ├── IGameModel.java
│   │   │   ├── IMovable.java
│   │   │   └── ITile.java
│   │   │
│   │   ├── map/                # Các thành phần bản đồ
│   │   │   ├── GameMap.java
│   │   │   └── Tile.java
│   │   │
│   │   └── GameModel.java
│   │
│   ├── view/                   # Các thành phần giao diện
│   │   ├── interfaces/         # Giao diện view
│   │   │   ├── IAnimation.java
│   │   │   ├── IGameView.java
│   │   │   └── IRenderer.java
│   │   │
│   │   ├── rendering/          # Thành phần hiển thị cốt lõi
│   │   │   ├── GamePanel.java
│   │   │   └── GameWindow.java
│   │   │
│   │   ├── renderers/          # Các trình vẽ thực thể
│   │   │   ├── BulletRenderer.java
│   │   │   ├── GrassRenderer.java
│   │   │   ├── PlayerRenderer.java
│   │   │   ├── WallRenderer.java
│   │   │   └── WaterRenderer.java
│   │   │
│   │   ├── Animation.java
│   │   ├── GameView.java
│   │   └── UIRenderer.java
│   │
│   └── Main.java               # Điểm khởi đầu ứng dụng
│
├── .gitignore                  # File Git ignore
├── README.md                   # Tài liệu dự án
└── XeTank_Java.iml             # File dự án IntelliJ
```

## Kiến trúc

Dự án này tuân theo kiến trúc Model-View-Controller (MVC):

- **Model**: Chứa trạng thái và logic của game
  - `GameModel`: Lớp model chính của game
  - Các lớp `Entity`: Player, Wall, Water, Bullet, v.v.
  - `GameMap`: Biểu diễn bản đồ

- **View**: Xử lý hiển thị và giao diện người dùng
  - `GameView`: Lớp vẽ chính
  - Các lớp vẽ cho từng loại thực thể
  - `Animation`: Xử lý các hình ảnh động
  - `GameWindow`/`GamePanel`: Các thành phần UI

- **Controller**: Xử lý đầu vào của người dùng và logic game
  - `GameController`: Vòng lặp chính và logic game
  - `KeyboardController`: Xử lý đầu vào từ bàn phím
  - `CollisionController`: Xử lý phát hiện va chạm

## Giao diện

Dự án sử dụng nhiều giao diện để đảm bảo tính kết hợp lỏng lẻo giữa các thành phần:

- `IEntity`, `IMovable`, `ICollidable`: Các giao diện hành vi thực thể
- `IGameModel`: Giao diện model
- `IGameView`, `IRenderer`: Giao diện view
- `IGameController`, `IInputController`: Giao diện controller

