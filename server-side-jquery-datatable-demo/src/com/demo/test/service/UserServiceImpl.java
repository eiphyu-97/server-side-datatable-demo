package com.demo.test.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.test.dao.UserDao;
import com.demo.test.entity.User;
import com.demo.test.entity.datatable.OrderingCriteria;
import com.demo.test.entity.datatable.PaginationCriteria;
import com.demo.test.entity.datatable.UserComparators;
import com.demo.test.exception.TableDataException;

@Service
public class UserServiceImpl extends  DataServiceBase<User> implements UserService {
	private static final Comparator<User> EMPTY_COMPARATOR = (u1, u2) -> 0;
	@Autowired
	private UserDao userdao;

	@Override
	public void save(User user) {
		userdao.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userdao.getUsers();
	}

	@Override
	public long countTotalEntries() throws TableDataException {
		return getUsers().size();
	}

	@Override
	public long countFilteredEntries(PaginationCriteria paginationCriteria) throws TableDataException {
		if(paginationCriteria.getSearch().getValue().isEmpty()) {
			return getUsers().size();
		}else {
			return getData(paginationCriteria).size();
		}
		
	}

	@Override
	protected List<User> getData(PaginationCriteria paginationCriteria) throws TableDataException {
		int start = paginationCriteria.getStart();
		int length = paginationCriteria.getLength();
		System.out.println("Start : "+start+ " , length : "+length);
		/**
		 * This code snippet order by column name first , then retrieve limited length of data
		 */
		/*
	 	OrderingCriteria order = paginationCriteria.getOrder().get(0);
		int columnIndex =order.getColumn();
		String columnName =paginationCriteria.getColumns().get(columnIndex).getData();
		String dir = order.getDir();
		userdao.getUserPageOrderByColumn(start, length, columnName, dir);*/
		
		//This code process retrieve limited data first then, sort and filter with search value
		return userdao.getUsersPage(start, length).
				stream()
				.sorted(sortUser(paginationCriteria))
				.filter(u -> u.getName().contains(paginationCriteria.getSearch().getValue()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Using user comparator to sort user object by column
	 * @param criteria
	 * @return
	 */
	public Comparator<User> sortUser(PaginationCriteria criteria){
		try {
			
			OrderingCriteria order = criteria.getOrder().get(0);
			int columnIndex =order.getColumn();
			String columnName =criteria.getColumns().get(columnIndex).getData();
			String dir = order.getDir();
			System.out.println("Sorting column : "+columnName+" , dir : "+dir);
			Comparator<User> usercomparator = UserComparators.getComparator(columnName, dir);
			if(usercomparator == null) {
				return EMPTY_COMPARATOR;
			}
			
			return usercomparator;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return EMPTY_COMPARATOR;
	}
	


	
}
