package by.tc.task05.utils;

import java.io.Serializable;
import java.util.*;

/**
 * {@link List} wrapper that adds 'last portion of data' boolean flag to list.
 * Flag is used by jsp pages to determine whether 'next page' link needs to be
 * displayed or not.
 *
 * @param <T>
 */
public class ListPart<T> implements List<T>, Serializable {

	private static final long serialVersionUID = -1178619997985981122L;
	private List<T> subList;
	private boolean lastPart = true;

	public ListPart() {
		this.subList = new ArrayList<T>();
	}

	public ListPart(List<T> subList) {
		this.subList = subList;
	}

	public ListPart(List<T> subList, boolean lastPart) {
		this.subList = subList;
		this.lastPart = lastPart;
	}


	public List<T> getSubList() {
		return subList;
	}

	public void setSubList(List<T> subList) {
		this.subList = subList;
	}

	@Override
	public int size() {
		return subList.size();
	}

	@Override
	public boolean isEmpty() {
		return subList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return subList.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return subList.iterator();
	}

	@Override
	public Object[] toArray() {
		return subList.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return subList.toArray(a);
	}

	@Override
	public boolean add(T t) {
		return subList.add(t);
	}

	@Override
	public boolean remove(Object o) {
		return subList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return subList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return subList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return subList.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return subList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return subList.retainAll(c);
	}

	@Override
	public void clear() {
		subList.clear();
	}

	@Override
	public T get(int index) {
		return subList.get(index);
	}

	@Override
	public T set(int index, T element) {
		return subList.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		subList.add(index, element);
	}

	@Override
	public T remove(int index) {
		return subList.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return subList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return subList.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return subList.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return subList.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return subList.subList(fromIndex, toIndex);
	}

	public boolean isLast() {
		return lastPart;
	}

	public void setLast(boolean lastPart) {
		this.lastPart = lastPart;
	}

	@Override
	public String toString() {
		return "ListPart{" + "subList=" + subList + ", lastPart=" + lastPart +
				'}';
	}
}
