package com.demo.test.entity.datatable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.demo.test.entity.User;
/**
 * This user comparator sort the column data by specific column name in asc or desc
 * @author EiPhyuPhyuThant
 *
 */
public class UserComparators {
	
	static class Key{
		String name;
		String dir;
		public String getName() {
			return name;
		}
		public String getDir() {
			return dir;
		}
		public Key(String name, String dir) {
			super();
			this.name = name;
			this.dir = dir;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dir == null) ? 0 : dir.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (dir == null) {
				if (other.dir != null)
					return false;
			} else if (!dir.equals(other.dir))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
		
		
		
	}
	
	static Map<Key, Comparator<User>> map = new HashMap<UserComparators.Key, Comparator<User>>();
	
	static {
		map.put(new Key("id", OrderingCriteria.ASC), Comparator.comparing(User::getId));
		map.put(new Key("id", OrderingCriteria.DESC), Comparator.comparing(User::getId).reversed());
		
		map.put(new Key("name", OrderingCriteria.ASC), Comparator.comparing(User::getName));
		map.put(new Key("name", OrderingCriteria.DESC), Comparator.comparing(User::getName).reversed());
		
		map.put(new Key("email", OrderingCriteria.ASC), Comparator.comparing(User::getEmail));
		map.put(new Key("email", OrderingCriteria.DESC), Comparator.comparing(User::getEmail).reversed());
	}
	
	public static Comparator<User> getComparator(String col,String dir){
		return map.get(new Key(col, dir));
		
	}

	private UserComparators() {
		
	}
}
