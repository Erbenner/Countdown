/**
 * @author Erbenner
 *
 */
package com.ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.data.Data;
import com.data.ReadData;
import com.data.WriteData;

public class AppBody extends JFrame {

	private static final long serialVersionUID = 1L;
	static JFrame f = new JFrame("倒计时 v1.0");

	public AppBody() {

		/* 对主窗口的初始化 */
		initJFrame();

		/* 向主窗口中添加若干JPanel用于显示数据 */
		initJPanel();

		// 刷新窗体
		f.setVisible(true);
	}

	/*
	 * 从excel中按行读取若干Data对象,并解析成String,生成对应的JLabel,然后再生成单独的JPanel,把JLabel加入其中,
	 * 最后在主窗口中添加改JPanel.事实上,如果不用JPanel把JLabel包装起来的话,会使得String在 主窗口中并排显示
	 * 
	 */
	private void initJPanel() {
		// TODO Auto-generated method stub
		ArrayList<Data> list = ReadData.spider("D:/workspace/data.xls");
		for (int i = 1; i < list.size(); i++) {
			Data temp = list.get(i);
			String str = "计算失败！";
			try {
				str = temp.upToFromNow();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel label = new JLabel(str);
			label.setFont(new Font("仿宋", 20, 20));
			label.setSize(800, 300);
			JPanel jp = new JPanel();
			jp.add(label);
			f.add(jp);
		}
	}

	private void initJFrame() {

		/* 基本的参数设定 */
		f.setSize(320, 600);
		f.setLocation(300, 200);
		f.setVisible(true);
		f.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* 菜单栏 */
		JMenuBar menuBar = new JMenuBar();
		f.setJMenuBar(menuBar);

		/* 菜单栏第一个按钮 */
		JMenu menu = new JMenu("操作");
		menu.setFont(new Font("宋体", 15, 15));
		menuBar.add(menu);

		/* 向第一个按钮中添加两个选项,并声明响应事件 */
		JMenuItem item1 = new JMenuItem("添加纪念日");
		item1.setFont(new Font("宋体", 15, 15));
		JMenuItem item2 = new JMenuItem("关闭");
		item2.setFont(new Font("宋体", 15, 15));

		/* 按钮一的事件响应为添加新纪念日 */
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				/*
				 * 框体设计
				 */

				JFrame dialog = new JFrame();

				// 布局设计
				dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
				dialog.setTitle("添加纪念日");
				dialog.setFont(new Font("仿宋", 20, 20));
				dialog.setSize(450, 200);
				dialog.setLocation(200, 200);

				/*
				 * 第一行，输入事件
				 */
				JPanel p1 = new JPanel();
				JLabel label1 = new JLabel("纪念的事件：");
				label1.setFont(new Font("仿宋", 20, 20));
				JTextField text1 = new JTextField(15);
				text1.setHorizontalAlignment(JTextField.LEFT);
				text1.setVisible(true);
				label1.setVisible(true);
				p1.add(label1);
				p1.add(text1);

				/*
				 * 第二行，输入日期
				 */
				JPanel p2 = new JPanel();
				JLabel date = new JLabel("纪念的日期：");
				JLabel year = new JLabel("年");
				JLabel month = new JLabel("月");
				JLabel day = new JLabel("日");
				date.setFont(new Font("仿宋", 20, 20));
				year.setFont(new Font("仿宋", 20, 20));
				month.setFont(new Font("仿宋", 20, 20));
				day.setFont(new Font("仿宋", 20, 20));
				JTextField text_year = new JTextField(3);
				JTextField text_month = new JTextField(3);
				JTextField text_day = new JTextField(3);
				p2.add(date);
				p2.add(text_year);
				p2.add(year);
				p2.add(text_month);
				p2.add(month);
				p2.add(text_day);
				p2.add(day);

				/*
				 * 添加按钮组件 ，并把text中三个参数传递出去
				 */

				JButton btn = new JButton("提交纪念日");
				btn.setFont(new Font("黑体", 15, 15));
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Data data = new Data();
						data.event = text1.getText();
						data.year = text_year.getText();
						data.month = text_month.getText();
						data.day = text_day.getText();
						// 格式化输入的数据
						data.format();

						JDialog dia = new JDialog(dialog, true);
						dia.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
						dia.setSize(180, 180);
						dia.setLocation(300, 400);
						dia.setTitle("提示");

						JPanel jp1 = new JPanel();
						JPanel jp2 = new JPanel();
						JPanel jp3 = new JPanel();

						boolean tag = WriteData.write(data);
						if (tag) {
							// 添加成功
							JLabel act1 = new JLabel("恭喜您！");
							JLabel act2 = new JLabel("添加成功！");
							act1.setFont(new Font("楷体", 20, 20));
							act1.setVisible(true);
							act2.setFont(new Font("楷体", 20, 20));
							act2.setVisible(true);
							jp1.add(act1);
							jp2.add(act2);
							// JLabel temp = new
							// JLabel(ReadData.dataToStr(data));
							// temp.setFont(new Font("仿宋", 20, 20));
							// temp.setSize(800, 300);
							// f.add(temp);
						} else {
							// 添加失败
							JLabel act1 = new JLabel("添加失败！");
							JLabel act2 = new JLabel("请重试！");
							act1.setFont(new Font("楷体", 20, 20));
							act1.setVisible(true);
							act2.setFont(new Font("楷体", 20, 20));
							act2.setVisible(true);
							jp1.add(act1);
							jp2.add(act2);
						}

						JButton b = new JButton("确定");
						b.setVisible(true);

						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								dia.dispose();
							}
						});

						jp3.add(b);
						dia.add(jp1);
						dia.add(jp2);
						dia.add(jp3);
						dia.setVisible(true);

						/* 关闭确定框 */
						dia.dispose();
						/* 关闭添加框 */
						dialog.dispose();

						f.setVisible(true);
					}
				});

				/*
				 * 把组件添加进Frame中，并设置可见
				 */
				dialog.add(p1);
				dialog.add(p2);
				dialog.add(btn);
				dialog.setVisible(true);
			}
		});

		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		menu.add(item1);
		menu.addSeparator();
		menu.add(item2);

	}
}
