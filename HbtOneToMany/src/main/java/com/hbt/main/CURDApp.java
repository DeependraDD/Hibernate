package com.hbt.main;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hbt.conf.HibernateUtils;
import com.hbt.model.Stock;
import com.hbt.model.StockDailyRecord;

public class CURDApp {
	SessionFactory sessionFactory = HibernateUtils.getObject();
	public void saveData() {
		
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Stock stk = new Stock();
			// stk.setStockId(100);
			stk.setStockCode("st111");
			stk.setStockName("Nirma");
			//session.save(stk);

			StockDailyRecord stkdaly = new StockDailyRecord();
			// stkdaly.setRecordId(1001);
			stkdaly.setPriceChange(100.45f);
			stkdaly.setPriceClose(200.0f);
			stkdaly.setPriceOpen(300f);
			stkdaly.setVolume(400l);
			stkdaly.setDate(new Date());
			stkdaly.setStock(stk);

			Set<StockDailyRecord> sdaly = new HashSet<StockDailyRecord>();
			sdaly.add(stkdaly);
			stk.setStockDailyRecords(sdaly);
			session.save(stk);
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	}

	public void getData() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Stock stkRecord = (Stock) session.get(Stock.class, 1);

			Set<StockDailyRecord> StockDailyRecord = stkRecord.getStockDailyRecords();
			for (StockDailyRecord stockDailyRecord2 : StockDailyRecord) {

				System.out.println(stockDailyRecord2.getDate());
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
