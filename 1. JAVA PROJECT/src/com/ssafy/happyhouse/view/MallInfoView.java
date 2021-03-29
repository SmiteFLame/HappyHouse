package com.ssafy.happyhouse.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.Mall.dto.MallInfo;
import com.ssafy.happyhouse.Mall.dto.MallPageBean;
import com.ssafy.happyhouse.Mall.service.MallService;
import com.ssafy.happyhouse.Mall.service.MallServiceImpl;


public class MallInfoView{
	
	/**model들 */
	private MallService 		mallService;
	
	/** main 화면 */
	private JFrame frame;
	
	/** 주택 목록 화면*/
	// JFrame owner;
	

	/**창 닫기 버튼*/
	//private JButton  closeBt;
	private JButton			  	newWindow;
	
	/**주택 이미지 표시 Panel*/
	private JLabel	 			imgL;
	private JLabel[] 			MallInfoL;
	
	/**조회 내용 표시할 table*/
	private DefaultTableModel 	MallModel;
	private JTable			  	MallTable;
	private JScrollPane		  	MallPan;
	private String[]		  	title = { "번호", "가게이름", "가게 상세 분류", "가게 대 분류", "도로명 보기"};
	
	/**검색  조건*/
	private String	key;
	
	/**검색할 단어*/
	private String  word;
	private boolean[] searchType = { true, true, true, true };
	private String[] choice = { "all", "도시", "구" };

	/**화면에 표시하고 있는 주택*/
	private MallInfo curMall;
	
	public MallInfoView(){
		/*Service들 생성 */
		mallService = new MallServiceImpl();
		setMain();
		//showMalls();
	}
	

	/**메인 화면인 주택 목록을 위한 화면 셋팅하는 메서드  */
	public void setMain(){
		
		/*왼쪽 화면을 위한 설정 */
		
		JFrame newFrame = new JFrame("");
		newFrame.setVisible(true);
		newFrame.setResizable(true);
		newFrame.setSize(1000, 600);
		newFrame.setLocation(1000, 200);
		newFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				newFrame.setVisible(false);
				newFrame.dispose();
			}
		});
	
		
		showMalls();
		

		JTable table = new JTable(showMalls(), title);
		JScrollPane scrollPane = new JScrollPane(table);
		newFrame.add(scrollPane);
	}
	
	
	/**
	 * 주택 목록을  갱신하기 위한 메서드 
	 */
	public String[][] showMalls(){
		MallPageBean  bean = new MallPageBean();
		
		List<MallInfo> deals = mallService.searchAll(bean);
		if(deals!=null){
			int i=0;
			String[][]data = new String[deals.size()][5];
			System.out.println(deals.size());
			for (MallInfo deal: deals) {
				if(key != null && !deal.getDongName().equals(key)) continue;
				data[i][0]= ""+deal.getNo();
				data[i][1]= deal.getShopName();
				data[i][2]= deal.getCode3();
				data[i][3]= deal.getCode4();
				data[i++][4]= deal.getDoroaddress();
			}
			return data;//MallModel.setDataVector(data, title);
		}
		return null;
	}
//	public static void main(String[] args) {
//		new MallInfoView();
//	}
	
}

