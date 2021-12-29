package xingming;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Count extends JFrame {
	private JFrame frame;
	private JTextField delay;
	private JTextPane showtime;
	private Date targetDate;
	private Timer timer;
	long longTime;
	
	long currentTime;
	
	long distTime;
	
	long day, hour, minutes, seconds;
	int del = 500;
	int flag = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Count frame = new Count();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Count() {
		timer = new Timer();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("\u65B0\u5E74\u5012\u8BA1\u65F6");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8DDD\u79BB2022\u5E74\u8FD8\u6709");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 17, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 40, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 102, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel_1);
		
		showtime = new JTextPane();
		showtime.setFont(new Font("宋体", Font.PLAIN, 57));
		springLayout.putConstraint(SpringLayout.NORTH, showtime, 23, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, showtime, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, showtime, 135, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, showtime, 394, SpringLayout.WEST, getContentPane());
		showtime.setForeground(Color.CYAN);
		showtime.setText(getSurplusDate());
		getContentPane().add(showtime);
		
		JLabel lblNewLabel_2 = new JLabel("\u5237\u65B0\u9891\u7387");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 182, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -22, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u6BEB\u79D2");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 0, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, showtime);
		getContentPane().add(lblNewLabel_3);
		
		JButton button_start = new JButton("\u505C\u6B62");
		button_start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag == 0) {
					button_start.setText("开始");
					try {
						timer.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					button_start.setText("停止");
					timer.notify();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, button_start, 31, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, button_start, 0, SpringLayout.SOUTH, lblNewLabel_2);
		getContentPane().add(button_start);
		
		delay = new JTextField();
		delay.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				String sdelay = delay.getText();
			    del = Integer.valueOf(sdelay).intValue();
			}
		});
		delay.setText("500");
		springLayout.putConstraint(SpringLayout.NORTH, delay, -3, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, delay, -12, SpringLayout.WEST, lblNewLabel_3);
		getContentPane().add(delay);
		delay.setColumns(10);

		timer.schedule(new TimerTask() {
			
		@Override
		
			public void run() {
				Calendar cal = Calendar.getInstance();
				
				cal.set(2022, 1, 1, 0, 0, 0);
				
				// 返回历元到指定时间的毫秒数。
				
				longTime = cal.getTimeInMillis();
				
				
				
				currentTime = new Date().getTime();
				
				distTime = longTime - currentTime;
				
				day = ((distTime / 1000) / (3600 * 24));
				
				hour = ((distTime / 1000) - day * 86400) / 3600;
				
				minutes = ((distTime / 1000) - day * 86400 - hour * 3600) / 60;
				
				seconds = (distTime / 1000) - day * 86400 - hour * 3600 - minutes * 60;
				
				showtime.setText( day*60*60*60 + hour*60*60 + minutes*60  + seconds + "秒");
				
			}
			
		}, 0, del);
	}
	
	
    public static String getSurplusDate() {
        String str = "";
        Calendar newYear = Calendar.getInstance();
        newYear.set(2022, 1, 1, 0, 0, 0);    //注意一定要把时分秒设置上  否则的话时间会一直固定
        Calendar cal = Calendar.getInstance();
    
        long time = newYear.getTimeInMillis() - cal.getTimeInMillis();
        
        time /= 1000;
        long day = 0,hour = 0,min = 0,second = 0;
        
        day = time / (24 * 60 * 60);
        hour = time / (60 * 60) - day * 24;
        min = time / (60) - day * 24 * 60 - hour * 60;
        second = time - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
//        str =  day + "天        " + hour + ":" +  min + ":" + second;
        
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.HOUR, (int)hour);
        temp.set(Calendar.MINUTE, (int)min);
        temp.set(Calendar.SECOND, (int)second);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        str = day + "天" + sdf.format(temp.getTime());
        return str;
    }

	private void addEventHandler(){
		new Thread(){
			public void run() {
				//每1秒更新jl2上的时间
				while(true){
					//当前时间
					Date now=new Date();
					
					//目标时间距离当前时间差的秒
					long time=(targetDate.getTime()-now.getTime())/1000;
					
					if(time<=0){

						
						
						break;
					}
					
					//根据time算出天，时，分，秒
					int day=(int) (time/(60*60*24));
					int hour=(int) ((time%(60*60*24))/(60*60));
					int mins=(int) ((time%(60*60))/60);
					int second=(int) (time%60);
					
					showtime.setText(day+"天"+hour+"时"+mins+"分"+second+"秒");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}


}
