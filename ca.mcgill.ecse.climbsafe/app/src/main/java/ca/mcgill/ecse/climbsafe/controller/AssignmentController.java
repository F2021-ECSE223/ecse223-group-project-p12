����   6 �  8ca/mcgill/ecse/climbsafe/controller/AssignmentController  java/lang/Object 	climbSafe *Lca/mcgill/ecse/climbsafe/model/ClimbSafe; <clinit> ()V Code
    9ca/mcgill/ecse/climbsafe/application/ClimbSafeApplication   getClimbSafe ,()Lca/mcgill/ecse/climbsafe/model/ClimbSafe;	     LineNumberTable LocalVariableTable <init>
     this :Lca/mcgill/ecse/climbsafe/controller/AssignmentController; initiateAssignments payTrip '(Ljava/lang/String;Ljava/lang/String;)V 
Exceptions  9ca/mcgill/ecse/climbsafe/controller/InvalidInputException
   " ! (ca/mcgill/ecse/climbsafe/model/ClimbSafe # $ findMemberFromEmail ;(Ljava/lang/String;)Lca/mcgill/ecse/climbsafe/model/Member;
  & ' ( equals (Ljava/lang/Object;)Z * java/lang/StringBuilder , Member with email address 
 ) .  / (Ljava/lang/String;)V
 ) 1 2 3 append -(Ljava/lang/String;)Ljava/lang/StringBuilder; 5  does not exist
 ) 7 8 9 toString ()Ljava/lang/String;
  .
 < & = java/lang/String ? Invalid authorization code
   A B C getAssignments ()Ljava/util/List; E G F java/util/List H I iterator ()Ljava/util/Iterator; K M L java/util/Iterator N O next ()Ljava/lang/Object; Q )ca/mcgill/ecse/climbsafe/model/Assignment
 P S T U 	getMember )()Lca/mcgill/ecse/climbsafe/model/Member;
   W X Y indexOfAssignment .(Lca/mcgill/ecse/climbsafe/model/Assignment;)I
   [ \ ] getAssignment .(I)Lca/mcgill/ecse/climbsafe/model/Assignment;
 P _ ` a setPaymentCode (Ljava/lang/String;)Z
 P c d e pay ()Z K g h e hasNext memberEmail Ljava/lang/String; authorizationCode a +Lca/mcgill/ecse/climbsafe/model/Assignment; StackMapTable 
startTrips (I)V
   r s t 
getNrWeeks ()I v Week number out of bounds
 P x y t getStartWeek
 P { | e start week I 
finishTrip
 � � � %ca/mcgill/ecse/climbsafe/model/Member \ � -()Lca/mcgill/ecse/climbsafe/model/Assignment;
 P � � e finish member 'Lca/mcgill/ecse/climbsafe/model/Member; 
cancelTrip 
SourceFile AssignmentController.java !                	   '      � 
� �                      	   /     *� �                        	    	   !       �                  	          	       �� *� � %�  � Y� )Y+� -*� 04� 0� 6� :�+� ;� � Y>� :�� � @� D N� A-� J � PM,� R� *� � %� &� � ,� V� Z+� ^W� � ,� V� Z� bW-� f ����       * 
       + ! 3 " = $ V % g & y ' � $ � *         � i j     � k j  V 4 l m  n    +�   < <  K  = 	 o p        	   �     U� � q� � � Yu� :�� � @� D M� &,� J � PL+� w� � � +� V� Z� zW,� f ��ױ           4  5 2 6 : 7 K 5 T 8        U } ~   2  l m  n    	�    K  " 	  /        	   �     4� 
L+*� M,�  � Y� )Y+� -*� 04� 0� 6� :�,� �� �W�           B  C 
 D + E 3 F         4 i j    0    
 * � �  n    � +   � 	 � /        	   +      �           O         i j    �    �