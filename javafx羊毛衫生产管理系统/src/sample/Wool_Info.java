package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

// 信息管理
class Wool_Info {

    public static TableView<Wool_Bean> table;

    ResultSet rst = null;
    Statement ppst = null;

    public Wool_Info() {

        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();

        Stage stage = new Stage();
        stage.show();  //  让窗口显示出来
        stage.setHeight(800);  // 设置窗口的高度
        stage.setWidth(1250);  // 设置窗口的宽度
        stage.setTitle("羊毛衫生产管理系统");  // 设置窗口的标题
        stage.setResizable(false);

        //  获取 电脑屏幕的大小
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();

        stage.setX(width / 2 - stage.getWidth() / 2);
        stage.setY(height / 2 - stage.getHeight() / 2 - 70);

        AnchorPane an = new AnchorPane();

        Scene scene = new Scene(an); // bor 是 方位布局
        stage.setScene(scene); // 设置场景

        // 创建一个 StackPane 用来包含文字
        StackPane stackPane = new StackPane();
        stackPane.setMinWidth(1250);
        stackPane.setMinHeight(50);
        // 设置区域背景颜色
        stackPane.setStyle("-fx-background-color: #FF6347;");  // 使用一个颜色，如番茄红
        // 创建文本并添加到区域
        Text text = new Text("欢  迎  进  入  羊  毛  衫  生  产  管  理  系  统  ！");
        text.setFont(new Font(30));
        stackPane.getChildren().add(text);
        AnchorPane.setTopAnchor(stackPane,10.0);
        AnchorPane.setLeftAnchor(stackPane,10.0);

        Button b4 = new Button ("查询");
        AnchorPane.setTopAnchor(b4,80.0);
        AnchorPane.setLeftAnchor(b4,800.0);
        b4.setFont(new Font(18));
        b4.setMinSize(150,38);

        Button b5 = new Button ("新增信息");
        AnchorPane.setTopAnchor(b5,630.0);
        AnchorPane.setLeftAnchor(b5,80.0);
        b5.setFont(new Font(18));
        b5.setMinSize(150,30);

        Button b2 = new Button ("修改信息");
        AnchorPane.setTopAnchor(b2,630.0);
        AnchorPane.setLeftAnchor(b2,360.0);
        b2.setFont(new Font(20));
        b2.setMinSize(150,30);

        Button b6 = new Button ("删除信息");
        AnchorPane.setTopAnchor(b6,630.0);
        AnchorPane.setLeftAnchor(b6,630.0);
        b6.setFont(new Font(18));
        b6.setMinSize(150,30);

        Button b7 = new Button ("退出登录");
        AnchorPane.setTopAnchor(b7,630.0);
        AnchorPane.setLeftAnchor(b7,910.0);
        b7.setFont(new Font(18));
        b7.setMinSize(150,30);

        Button b8 = new Button ("质量不合格订单");
        AnchorPane.setTopAnchor(b8,700.0);
        AnchorPane.setLeftAnchor(b8,230.0);
        b8.setFont(new Font(18));
        b8.setMinSize(150,30);

        Button b9 = new Button ("未完成订单");
        AnchorPane.setTopAnchor(b9,700.0);
        AnchorPane.setLeftAnchor(b9,500.0);
        b9.setFont(new Font(18));
        b9.setMinSize(150,30);

        Button b10 = new Button ("全部订单");
        AnchorPane.setTopAnchor(b10,700.0);
        AnchorPane.setLeftAnchor(b10,770.0);
        b10.setFont(new Font(18));
        b10.setMinSize(150,30);

        TextField t1 = new TextField();
        t1.setMinSize(500,60);
        t1.setLayoutX(230);
        t1.setLayoutY(70);
        t1.setPromptText("请输入需要查询的羊毛衫名称");


        // 修改信息
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Wool_Bean row =  table.getSelectionModel().getSelectedItem();
                if (row == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("请选择您要修改的信息!");
                    alert.setResizable(false);
                    alert.showAndWait();
                }else {
                    int s =row.getId();
                    Update_Info updateInfo = new Update_Info(s,stage);
                }

            }
        });


        // 查询信息
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String s = t1.getText();
                String sql3 = "select * from wool_sweater  where (device_name like '"+s+"%'||device_name like '%"+s+"'||device_name like '%"+s+"%'|| device_name = '"+s+"')";

                if (s.isEmpty()){
                    sql3 = "select * from wool_sweater";
                }

                try {
                    Login.woolInfo.date(table,sql3);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // 新增信息
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Add_Info Add_Info = new Add_Info(stage);
            }
        });


        // 退出登录
        b7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                Login login = new Login();
            }
        });

        // 删除信息
        b6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Wool_Bean row =  table.getSelectionModel().getSelectedItem();
                if (row == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("请选择您要删除的羊毛衫信息!");
                    alert.setResizable(false);
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("你确定要删除该羊毛衫信息吗？");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK){

                        int s = row.getId();
                        String sql1 = "delete from wool_sweater where id = ?;";
                        PreparedStatement preparedStatement=null;
                        try {
                            preparedStatement=conn.prepareStatement(sql1);
                            preparedStatement.setInt(1,s);
                            preparedStatement.executeUpdate();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        try {
                            date(table,"SELECT * from wool_sweater order by id ASC");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }


                }


            }
        });

        // 质量不合格订单
        b8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    date(table,"SELECT * from wool_sweater where quality_status != '优秀' order by id ASC");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // 未完成订单
        b9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    date(table,"SELECT * from wool_sweater where production_progress != '100%' order by id ASC");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // 全部订单
        b10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    date(table,"SELECT * from wool_sweater order by id ASC");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


//        导入数据库的表单
//        --------------------------------------------------------------------

        table = new TableView();

            //定义表格的行标
        TableColumn id = new TableColumn("编号");
        TableColumn name = new TableColumn("名称");
        TableColumn color = new TableColumn("颜色");
        TableColumn size = new TableColumn("尺寸");
        TableColumn price = new TableColumn("价格");
        TableColumn order_quantity = new TableColumn("订单数量");
        TableColumn production_progress = new TableColumn("生产进度");
        TableColumn material_info = new TableColumn("原材料信息");
        TableColumn quality_status = new TableColumn("质量情况");

        // 设置 表格整体高度
        table.setStyle(
                "-fx-pref-height: 440px;\n" +
                        "-fx-font-size: 20;\n"   // 字体大小
        );


        //表格列宽宽度设置
        id.setMinWidth(80);
        name.setMinWidth(150);
        color.setMinWidth(130);
        size.setMinWidth(100);
        price.setMinWidth(150);
        order_quantity.setMinWidth(100);
        production_progress.setMinWidth(150);
        material_info.setMinWidth(150);
        quality_status.setMinWidth(190);

            //确定数据导入的列               后面括号里面对应数据库里面的标头
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        name.setCellValueFactory(
                    new PropertyValueFactory<>("name"));
        color.setCellValueFactory(
                    new PropertyValueFactory<>("color"));
        size.setCellValueFactory(
                    new PropertyValueFactory<>("size"));
        price.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        order_quantity.setCellValueFactory(
                new PropertyValueFactory<>("order_quantity"));
        production_progress.setCellValueFactory(
                new PropertyValueFactory<>("production_progress"));
        material_info.setCellValueFactory(
                new PropertyValueFactory<>("material_info"));
        quality_status.setCellValueFactory(
                new PropertyValueFactory<>("quality_status"));


            //向表中导入数据

        table.getColumns().addAll(id, name, color,size,price,order_quantity,production_progress,material_info,quality_status);
        an.getChildren().addAll(table,b2,b4,t1,b5,b6,b7,stackPane,b8,b9,b10);
        AnchorPane.setLeftAnchor(table,16.0);
        AnchorPane.setTopAnchor(table,160.0);

        String sql4 = "SELECT * from wool_sweater order by id ASC";
        try {
            date(table,sql4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

        // 传值的方法
        // 表中能够展示 数据的 方法
        public void date(TableView table ,String s)throws ClassNotFoundException {

            DBUtil dbUtil = new DBUtil();
            Connection conn = dbUtil.getConn();
            // 适用于TableView的集合，存数据
            ObservableList<Wool_Bean> data = FXCollections.observableArrayList();
            try {
                ppst = conn.createStatement();  // 执行操作
                rst = ppst.executeQuery(s);  // 查询结果的存储功能
                while(rst.next()) {
                    data.add(new
                            Wool_Bean(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9)));
                    table.setItems(data); // 集合联系javafx中的表
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }



}
