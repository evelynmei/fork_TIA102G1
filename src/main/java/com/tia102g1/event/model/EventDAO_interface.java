package com.tia102g1.event.model;

import java.util.*;

public interface EventDAO_interface {
		public void insert(EventVO eventVO);
		public void update(EventVO eventVO);
		public void delete(Integer eventId);
		public EventVO findByPrimaryKey(Integer eventId);
		public List<EventVO> getAll();
}
