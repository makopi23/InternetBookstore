/*
    This is a sample class from the Internet Bookstore example project,
    for the book "Use Case Driven Object Modeling with UML - Theory and Practice".
    Copyright (c) Doug Rosenberg and Matt Stephens, 2007.
    
    For more info, see: http://www.iconixprocess.com/books/use-case-driven/
    
    Some of the code is "boilerplate" or adapted from the Spring Framework examples. For more
    info on Spring Framework, please see: http://www.springframework.org/
    
    Please note: This code is for learning purposes only, and comes with no warranty of any kind.
    
    This code represents the Bookstore at various stages of development and review,
    as described in the "Use Case Driven" book. We encourage you to compare this code with
    the models illustrated in the book, and especially with the "review" chapters. Have fun!
*/
package com.iconixsw.bookstore;

import com.iconixsw.bookstore.domain.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReflectUtil
{
 /**
  * String to look for DIFF Values
  */
 public final static String DIFF = "!*";
  
    public static void main(String[] args) {
        System.out.println(ReflectUtil.toString(new Book()));
    }
 
 public static String toString( Object o )
 {
  List visited = new ArrayList();
  
  int inset = 0;
  if( o == null ) return "null\n";
    
  if( Collection.class.isAssignableFrom( o.getClass() ) )
  {
   return toString( (Collection)o, inset+1, visited );
  }
  else
  if( Map.class.isAssignableFrom( o.getClass() ) )
  {
   return toString( (Map)o, inset+1, visited );
  }
  else
  if( o.getClass().isArray() )
  {
   return toStringArray( o, inset+1, visited );
  }
  else
  if( o.getClass().isPrimitive() || o.getClass().getName().startsWith( "java." ))
  {
   return o.getClass().getName() + " " + o;
  }
  else
  {
   return toString( o, inset+1, visited );
  }
 }
 
 private static String toString( Collection c, int inset, List visited )
 {
  if( c == null ) return " null\n";
  if( visited.contains(c ) ) return "repeat " + c + "\n";
  visited.add( c );
  
  StringBuffer buf = new StringBuffer();
  
  buf.append( tab(inset-1) + c.getClass() + " " + c.size()  + "\n" ) ;
  buf.append( tab(inset-1) + "(\n" );
  Iterator iterator = c.iterator();
  for( int index =0; iterator.hasNext(); index++ )
  {
   buf.append( tab(inset) + index + "=" + toString( iterator.next(), inset+1, visited ) );
  }
  buf.append( tab(inset-1) + ")" );
  return buf.toString();
 }
 
 private static String toString( Map m, int inset, List visited )
 {
  if( m == null ) return " null\n";
  if( visited.contains( m ) ) return "repeat " + m +"\n";
  visited.add( m );
  
  StringBuffer buf = new StringBuffer();
  
  buf.append( tab(inset-1) + m.getClass() + " " + m.size()  + "\n" ) ;
  buf.append( tab(inset-1) + "(\n" );
  Iterator iterator = m.entrySet().iterator(); 
  for( int index =0; iterator.hasNext(); index++ )
  {
   Map.Entry entry = (Map.Entry)iterator.next();
   buf.append( tab(inset) + "" + entry.getKey().getClass() + " " + entry.getKey() + "=" + toString( entry.getValue(), inset+1, visited ) +"\n");
  }
  buf.append( tab(inset-1) + ")" );
  return buf.toString();
 }
 private static String toStringArray( Object a, int inset, List visited )
 {
  if( a == null ) return " null\n";
  if( visited.contains( a ) ) return "repeat " + a + "\n";
  visited.add( a );
  
  StringBuffer buf = new StringBuffer();
  buf.append( tab(inset-1) + a.getClass().getComponentType() + " " + Array.getLength(a) + "\n" );
  buf.append( tab(inset-1) + "[\n" );
  
  for( int index =0; index < Array.getLength(a); index++ )
  {
   if( a.getClass().getComponentType().isPrimitive() || a.getClass().getComponentType().getName().startsWith( "java." ) )
   {
    buf.append( tab(inset) + Array.get(a,index)+ "\n" );
   }
   else
   {
    buf.append( tab(inset) + index + "=" + toString( Array.get(a,index), inset+1, visited ) );
   }
  }
  buf.append( tab(inset-1) + "]" );
  return buf.toString();
 }
 private static String toString( Object o, int inset, List visited )
 {
  if( o == null ) return " null\n";
  if( visited.contains( o ) ) return  " repeat "+ o + "\n";
  visited.add( o );
  
  StringBuffer buf = new StringBuffer();
  
  buf.append( tab(inset-1) + o.getClass() + "\n" );
  buf.append( tab(inset-1) + "{\n" );
  
  for( Class clazz = o.getClass(); clazz != null && !clazz.getName().startsWith( "java."); clazz = clazz.getSuperclass() )
  {
   if( o.getClass() != clazz ) buf.append( tab(inset) + "+ super class " + clazz + "\n" );
   buf.append( toFieldString( clazz, o, inset, visited ) );
  }
  
  buf.append( tab(inset-1) + "}" );
  return buf.toString();
 }
 
 private static String toFieldString( Class clazz, Object o, int inset, List visited )
 {
  StringBuffer buf = new StringBuffer();
 
  try
  {
   Field[] fields = clazz.getDeclaredFields();
 
   for( int index = 0; index < fields.length; index++ )
   {
    Field field = fields[index];
    field.setAccessible( true );
      
    if( Modifier.isStatic( field.getModifiers() ) ) continue;
    
    Object val = field.get(o);
    
    if( Collection.class.isAssignableFrom( field.getType() ) )
    {
     buf.append( tab(inset) + field.getName() + toString( (Collection)val, inset+1, visited ) + "\n" );
    }
    else
    if( field.getType().isArray() )
    {
     buf.append( tab(inset) + field.getName() + toStringArray( val, inset+1, visited )+ "\n" );
    }
    else
    if( field.getType().isPrimitive() || 
     ( val == null && field.getType().getName().startsWith( "java." ) ) ||
     ( val != null &&  val.getClass().getName().startsWith( "java." ) ) )
    {
     buf.append( tab(inset) + field.getName() + " " + field.getType().getName() + " " + val+ "\n" );
    }
    else
    {
     buf.append( tab(inset)+ field.getName() + toString( val, inset+1, visited )+ "\n" );
    }
   }
  }
  catch( Exception  e )
  {
     e.printStackTrace();
     buf.append( tab(inset) + e.getClass().getName() + " " + e.getMessage() );
  }
  
  return buf.toString();
 }
 
 public static String diff( Object o1, Object o2 )
 {
  List visited1 = new ArrayList();
  List visited2 = new ArrayList();
  
  int inset = 0;
  if( o1 == null && o2 == null ) return "null == null";
  if( o1 == null ) return flag(inset)+o2.getClass().getName() + " null != "+o2;
  
  if( !o1.getClass().equals( o2.getClass() ) ) return flag(inset)+"class=" + o1.getClass().getName() + " != " + o2.getClass().getName();
    
  StringBuffer buf = new StringBuffer();
  try
  {
   buf.append( "* diff ********************************\n");
  
   if( Collection.class.isAssignableFrom( o1.getClass() ) )
   {
    buf.append( tab(inset)+diff( (Collection)o1, (Collection)o2, inset+1, visited1, visited2 ) );
   }
   else
   if( o1.getClass().isArray() ) 
   {
    buf.append( tab(inset)+diffArray( o1, o2, inset+1, visited1, visited2 ) );
   }
   else
   {
    buf.append( tab(inset)+diff( o1, o2, inset+1, visited1, visited2 ) );
   }
  
  }
  catch( Throwable t )
  {
   t.printStackTrace();
   buf.append( t.getMessage() );
  }
   
  return buf.toString();
 }
 
   private static String tab( int inset )
   {
    StringBuffer buf = new StringBuffer( inset );
    for( int index = 0; index < inset; index++ )
    {
     buf.append( "  " );
    }
    return buf.toString();
   }
   
   private static String flag( int inset )
   {
    StringBuffer buf = new StringBuffer( inset );
    buf.append( "!*" );
    for( int index = 0; index < inset-1; index++ )
    {
     buf.append( "  " );
    }
    return buf.toString();
   }
   
   private static String diff( Map m1, Map m2, int inset, List visited1, List visited2 )
   {
    if( m1 != null && visited1.contains(m1) ) return "repeat 1";
    if( m2 != null && visited2.contains(m2) ) return "repeat 2";
    visited1.add(m1);
    visited2.add(m2);    
     
    if( m1 == null && m2 == null ) return tab(inset)+"null==null";
    if( m1 == null ) return flag(inset)+m2.getClass().getName() + " null!= "+m2;
    if( m1 == m2 ) return tab(inset)+m1.getClass().getName()+ " " +m1+" == "+m2;
    if( !m1.getClass().equals( m2.getClass() ) ) return flag(inset)+" class " + m1.getClass().getName() + " != " + m2.getClass().getName();
    
    StringBuffer buf = new StringBuffer();
    
    buf.append( tab(inset)+m1.getClass()+"(\n" );
    Iterator i1 = m1.keySet().iterator();
    Iterator i2 = m2.keySet().iterator();
    for( int index = 0; i1.hasNext() || i2.hasNext(); index++ )
    {
     if( i1.hasNext() && i2.hasNext() )
     {
      Object k1 = i1.next();
      Object k2 = i2.next();
      if( k1.equals( k2 ) )
      {
       Object o1 = m1.get(k1);
       Object o2 = m1.get(k2);
       buf.append( tab(inset)+ "key " + index + " " + k1 + " equals " +  k2 + " " );
       buf.append( tab(inset)+ "value " + index + " " + diff( o1, o2, inset+1, visited1, visited2 ) + "\n" );
      }
      else
      {
       buf.append( flag(inset)+ "key " + index + " " + k1 + " !equals " +  k2 );
       buf.append( diffString( (String)k1, (String)k2 ) );
      }
     }
     else
     if( i1.hasNext() )
     {
      buf.append( tab(inset)+index+ diff( i1.next(), null, inset+1, visited1, visited2 ) + "\n" );
     }
     else
     if( i2.hasNext() )
     {
      buf.append( tab(inset)+index+ diff( null, i2.next(), inset+1, visited1, visited2 ) + "\n" );
     }
    }
    buf.append( tab(inset)+")" );
    
    return buf.toString();
   }
   
   private static String diffString( String s1, String s2 )
   {
    StringBuffer buf = new StringBuffer();
    buf.append( "String Diff s1.len=" + s1.length() + " / s2.len=" + s2.length() + " / \"" );
    for( int index = 0; index < s1.length() || index < s2.length(); index++ )
    {
     Character c1 = index < s1.length() ? new Character(s1.charAt(index)) : null;
     Character c2 = index < s2.length() ? new Character(s2.charAt(index)) : null;
     
     if( c1 != null && c2 != null && c1.equals( c2 ) )
     {
      buf.append( c1 );
     }
     else
     {
      buf.append( "\" - Character "+index+ " [" + c1 + "] != [" + c2 + "]\n");
      break;
     }
    }
    buf.append( "\n");
    return buf.toString();
   }
   
   private static String diff( Collection c1, Collection c2, int inset, List visited1, List visited2 )
   {
    if( c1 != null && visited1.contains(c1) ) return "repeat 1";
    if( c2 != null && visited2.contains(c2) ) return "repeat 2";
    visited1.add(c1);
    visited2.add(c2);    
     
    if( c1 == null && c2 == null ) return tab(inset)+"null==null";
    if( c1 == null ) return flag(inset)+c2.getClass().getName() + " null!= "+c2;
    if( c1 == c2 ) return tab(inset)+c1.getClass().getName()+ " " +c1+" == "+c2;
    if( !c1.getClass().equals( c2.getClass() ) ) return flag(inset)+" class " + c1.getClass().getName() + " != " + c2.getClass().getName();
    
    StringBuffer buf = new StringBuffer();
    
    buf.append( tab(inset)+c1.getClass()+"(\n" );
    Iterator i1 = c1.iterator();
    Iterator i2 = c2.iterator();
    for( int index = 0; i1.hasNext() || i2.hasNext(); index++ )
    {
     if( i1.hasNext() && i2.hasNext() )
     {
      buf.append( tab(inset)+index+ diff( i1.next(), i2.next(), inset+1, visited1, visited2 ) + "\n" );
     }
     else
     if( i1.hasNext() )
     {
      buf.append( tab(inset)+index+ diff( i1.next(), null, inset+1, visited1, visited2 ) + "\n" );
     }
     else
     if( i2.hasNext() )
     {
      buf.append( tab(inset)+index+ diff( null, i2.next(), inset+1, visited1, visited2 ) + "\n" );
     }
    }
    buf.append( tab(inset)+")" );
    
    return buf.toString();
   }
   
   
   private static String diffArray( Object a1, Object a2, int inset, List visited1, List visited2  )
   {
    if( a1 != null && visited1.contains(a1) ) return "repeat 1";
    if( a2 != null && visited2.contains(a2) ) return "repeat 2";
    visited1.add(a1);
    visited2.add(a2);    
    
    if( a1 == null && a2 == null ) return tab(inset)+"null==null";
    if( a1 == null ) return flag(inset)+a2.getClass().getName() + " null!= "+a2;
    if( a1 == a2 ) return tab(inset)+a1.getClass().getName()+ " " +a1+" == "+a2;
    if( !a1.getClass().equals( a2.getClass() ) ) return flag(inset)+"class=" + a1.getClass().getName() + " != " + a2.getClass().getName();
    
    StringBuffer buf = new StringBuffer();
    
    buf.append( tab(inset)+a1.getClass()+"[\n" );
    for( int index = 0; index < Array.getLength(a1); index++ )
    {
     buf.append( tab(inset)+index+ diff( Array.get(a1,index), Array.get(a2,index), inset+1, visited1, visited2 ) + "\n" );
    }
    buf.append( tab(inset)+"]" );
    
    return buf.toString();
   }
   
   private static String diff( Object o1, Object o2, int inset, List visited1, List visited2 )
   {
    if( o1 != null && visited1.contains(o1) ) return "repeat 1";
    if( o2 != null && visited2.contains(o2) ) return "repeat 2";
    visited1.add(o1);
    visited2.add(o2);
    
    
    if( o1 == null && o2 == null ) return tab(inset)+"null == null";
 
    StringBuffer buf = new StringBuffer();
    
    if( o1 == null ) return flag(inset)+o2.getClass().getName() + " null!= "+o2;
    if( o2 == null ) return flag(inset)+o1.getClass().getName() + " null!= "+o1;
 
    if( o1 == o2 ) return tab(inset)+o1.getClass().getName()+ " " +o1+" == "+o2;
    
    if( !o1.getClass().equals( o2.getClass() ) )
    {
     return flag(inset)+o1.getClass().getName() + " class!= " + o2.getClass().getName();
    }
 
    Field[] fields1 = o1.getClass().getDeclaredFields();
    
    if( o1.getClass().isPrimitive() || o1.getClass().getName().startsWith( "java." ) )
    {
     if( o1.equals(o2) )
     {
      return tab(inset)+o1.getClass().getName() + " " + o1 + " equals " + o2;
     }
     else
     if( o1 instanceof String )
     {
      return diffString( (String)o1, (String)o2 )+flag(inset)+o1.getClass().getName() + " " + o1 + " !equals " + o2;
     }
     else
     {
      return flag(inset)+o1.getClass().getName() + " " + o1 + " !equals " + o2;
     }
    }
    
    buf.append( tab(inset-1)+o2.getClass().getName() +"\n" );
    buf.append( tab(inset-1)+"{\n" );
    
    try
    {
     for( int index = 0; index < fields1.length; index++ )
     {
      Field field1 = fields1[index];
      field1.setAccessible( true );
      if( Modifier.isStatic( field1.getModifiers() ) ) continue;
      if( Modifier.isTransient( field1.getModifiers() ) ) continue;
      
      Object val1 = field1.get(o1);
      Object val2 = field1.get(o2);
 
      if( field1.getType().equals( Object.class) )
      {
       buf.append( tab(inset)+ field1.getName() + " Object " + (val1==val2?"==":"!=") + " Object \n" );
      }
      else
      if( field1.getType().equals( Class.class) )
      {
       buf.append( tab(inset)+ field1.getName() + "\n" );
      }
      else
      if( field1.getType().isArray() )
      {
       buf.append( tab(inset)+ field1.getName() + diffArray( val1, val2, inset+1, visited1, visited2 ) + "\n" );
      }
      else
      if( Collection.class.isAssignableFrom( field1.getType() ) )
      {
       buf.append( tab(inset)+ field1.getName() + diff( (Collection)val1, (Collection)val2, inset+1, visited1, visited2 ) + "\n" );
      }
      else
      if( Map.class.isAssignableFrom( field1.getType() ) )
      {
       buf.append( tab(inset)+ field1.getName() + diff( (Map)val1, (Map)val2, inset+1, visited1, visited2 ) + "\n" );
      }
      else
      if( field1.getType().isPrimitive() || field1.getType().getName().startsWith( "java.") )
      {
       buf.append( tab(inset) + field1.getName() + diff( val1, val2, inset+1, visited1, visited2 ) + "\n" );
      }
      else
      {
       buf.append( tab(inset) + field1.getName() + diff( val1, val2, inset+1, visited1, visited2 ) + "\n" );
      }
     }
    }
    catch( Exception e )
    {
     e.printStackTrace();
     buf.append( tab(inset)+ e.getClass().getName() + " " + e.getMessage() + "\n" );
    }
 
    buf.append( tab(inset-1)+"}\n" );
    
    return buf.toString();
   }
 
 
 
}
