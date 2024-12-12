package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.*;
import java.sql.*;

public class Login {

    private final Stage stage1 = new Stage();

    public static Wool_Info woolInfo;

    public Login() {

        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        AnchorPane an = new AnchorPane();
        Scene sc=new Scene(an);

        stage1.setTitle("登录页面");
        stage1.setScene(sc);
        stage1.show();
        stage1.setResizable(false);
        stage1.setWidth(560);
        stage1.setHeight(380);

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width/2-350;
        int centerY = screenSize.height/2-200-100;

        stage1.setX(centerX);
        stage1.setY(centerY);

        Text user_name = new Text("用户名:");
        user_name.setFont(new Font(18));
        Text user_password = new Text("密码:");
        user_password.setFont(new Font(18));

        TextField text = new TextField();
        text.setMinSize(500,30);
        PasswordField pass= new PasswordField();
        pass.setMinSize(500,30);

        Button b1=new Button("登录");
        b1.setMinSize(500,30);
        b1.setFont(Font.font(18));
        Button b2=new Button("注册");
        b2.setMinSize(500,30);
        b2.setFont(Font.font(18));

        an.getChildren().addAll(user_name,user_password,text,pass,b1,b2);
        AnchorPane.setLeftAnchor(user_name,Double.valueOf(30));
        AnchorPane.setTopAnchor(user_name,Double.valueOf(20));
        AnchorPane.setLeftAnchor(user_password,Double.valueOf(30));
        AnchorPane.setTopAnchor(user_password,Double.valueOf(110));
        AnchorPane.setLeftAnchor(text,Double.valueOf(35));
        AnchorPane.setTopAnchor(text,Double.valueOf(60));
        AnchorPane.setLeftAnchor(pass,Double.valueOf(35));
        AnchorPane.setTopAnchor(pass,Double.valueOf(150));
        AnchorPane.setLeftAnchor(b1,Double.valueOf(35));
        AnchorPane.setTopAnchor(b1,Double.valueOf(210));
        AnchorPane.setLeftAnchor(b2,Double.valueOf(35));
        AnchorPane.setTopAnchor(b2,Double.valueOf(270));


        // 点击登录
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                String sql = "select account,password from user where account=? and password=?";
                PreparedStatement ptmt = null;
                ResultSet rs = null;
                try {

                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, text.getText());
                    ptmt.setString(2, pass.getText());
                    rs = ptmt.executeQuery();
                    //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
                    if (rs.next()) {
                        String demo = rs.getString(1);
                        woolInfo =new Wool_Info();
                        stage1.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("登录失败!");
                        alert.setResizable(false);
                        alert.showAndWait();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        // 注册页面
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                AnchorPane zhuce = new AnchorPane();
                Stage zhuce1 = new Stage();
                zhuce1.initOwner(stage1);
                zhuce1.initModality(Modality.WINDOW_MODAL);
                Scene zhuce2 = new Scene(zhuce);
                zhuce1.setScene(zhuce2);

                Text account = new Text("账号: ");
                Text password = new Text("密码: ");
                Text password2 = new Text("确认密码: ");
                account.setLayoutX(50);
                account.setLayoutY(50);
                account.setFont(new Font(18));
                password.setLayoutX(50);
                password.setLayoutY(110);
                password.setFont(new Font(18));
                password2.setLayoutX(50);
                password2.setLayoutY(170);
                password2.setFont(new Font(18));

                TextField text2 = new TextField();
                text2.setLayoutX(150);
                text2.setLayoutY(30);

                PasswordField pass1 = new PasswordField();
                pass1.setLayoutX(150);
                pass1.setLayoutY(90);

                PasswordField pass2 = new PasswordField();
                pass2.setLayoutX(150);
                pass2.setLayoutY(150);

                Button button1 = new Button("注册");
                button1.setLayoutX(30);
                button1.setLayoutY(210);
                button1.setMinSize(430,30);

                Button button2 = new Button("返回登录");
                button2.setLayoutX(30);
                button2.setLayoutY(260);
                button2.setMinSize(430,30);

                //   点击注册后的 判断
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //  判断 能不能 注册成功
                        if (text2.getText().isEmpty() || pass1.getText().isEmpty() ) {  // 判断输入内容是否为空
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setContentText("请输入完整的信息!");
                            alert.setResizable(false);
                            alert.showAndWait();
                        } else if (text2.getText().indexOf(" ")!=-1||pass1.getText().indexOf(" ")!=-1 ){
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setContentText("请勿输入非法字符!");
                            alert.setResizable(false);
                            alert.showAndWait();
                        }else if (pass1.getText().equals(pass2.getText())) {
                            // 判断是否 重复创建账号-------------
                            String sql = "select account from user where account=? ";
                            PreparedStatement ptmt = null;
                            ResultSet rs=null;
                            try {
                                ptmt=conn.prepareStatement(sql);
                                ptmt.setString(1,text2.getText());
                                rs=ptmt.executeQuery();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            try {
                                if (rs.next()) {  // 账号已经存在
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setHeaderText(null);
                                    alert.setContentText("您输入的账号已经存在！");
                                    alert.setResizable(false);
                                    alert.showAndWait();
                                }else {  //   账号不存在，可以注册
                                    zhuce1.close();
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setHeaderText(null);
                                    alert.setContentText("注册成功！");
                                    alert.setResizable(false);
                                    alert.showAndWait();
//   增加账号模块
//--------------------------------------------------------------------------------------------------------
                                    PreparedStatement pst = null;

                                        try {
                                            pst = conn.prepareStatement("insert into user (account ,password) values(?,?)");
                                            pst.setString(1, text2.getText());
                                            pst.setString(2, pass1.getText());
                                            pst.execute();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }


                        }
//--------------------------------------------------------------------------------------------------------

                    }
                });

                button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        zhuce1.close();
                    }
                });

                zhuce.getChildren().addAll(text2,pass1,button1,pass2,account,password2,password,button2);

                zhuce1.setTitle("注册页面");
                zhuce1.setWidth(500);
                zhuce1.setHeight(380);
                zhuce1.setResizable(false);
                zhuce1.show();


            }
        });


    }


}
