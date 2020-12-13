package otherClasses;

import java.util.*;

public class ArtikelList extends ArrayList implements List {
    private ArrayList <Artikel> artikelArrayList ;

    public ArtikelList(int initialCapacity, ArrayList<Artikel> artikelArrayList) {
        super(initialCapacity);
        this.artikelArrayList = artikelArrayList;
    }

    public ArtikelList(ArrayList<Artikel> artikelArrayList) {
        this.artikelArrayList = artikelArrayList;
    }

    public ArtikelList(Collection c, ArrayList<Artikel> artikelArrayList) {
        super(c);
        this.artikelArrayList = artikelArrayList;
    }

    public ArtikelList() {

        artikelArrayList = new ArrayList<Artikel>();
    }

    @Override
    public int size() {
        return artikelArrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return artikelArrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Artikel[] toArray() {
        return new Artikel[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }


    public boolean add(Artikel o) {
        return false;
    }
}
