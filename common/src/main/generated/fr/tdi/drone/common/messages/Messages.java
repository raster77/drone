// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package fr.tdi.drone.common.messages;

public final class Messages {
  private Messages() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Position_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Position_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Drone_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Drone_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DroneMove_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DroneMove_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Zone_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Zone_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016messages.proto\"C\n\010Position\022\t\n\001x\030\001 \001(\005\022" +
      "\t\n\001y\030\002 \001(\005\022!\n\013orientation\030\003 \001(\0162\014.Orient" +
      "ation\"J\n\005Drone\022\n\n\002id\030\001 \001(\005\022\033\n\010position\030\002" +
      " \001(\0132\t.Position\022\030\n\005moves\030\003 \003(\0162\t.Movemen" +
      "t\"1\n\tDroneMove\022\n\n\002id\030\001 \001(\005\022\030\n\005moves\030\002 \003(" +
      "\0162\t.Movement\"%\n\004Zone\022\r\n\005width\030\001 \001(\005\022\016\n\006h" +
      "eight\030\002 \001(\005\";\n\007Message\022!\n\013messageType\030\001 " +
      "\001(\0162\014.MessageType\022\r\n\005datas\030\002 \001(\014*9\n\013Orie" +
      "ntation\022\t\n\005NORTH\020\000\022\010\n\004EAST\020Z\022\n\n\005SOUTH\020\264\001" +
      "\022\t\n\004WEST\020\216\002*\037\n\010Movement\022\005\n\001A\020\000\022\005\n\001R\020\001\022\005\n",
      "\001L\020\002*,\n\013MessageType\022\010\n\004ZONE\020\000\022\t\n\005DRONE\020\001" +
      "\022\010\n\004MOVE\020\002B\"\n\034fr.tdi.drone.common.messag" +
      "esH\001P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Position_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Position_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Position_descriptor,
        new java.lang.String[] { "X", "Y", "Orientation", });
    internal_static_Drone_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Drone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Drone_descriptor,
        new java.lang.String[] { "Id", "Position", "Moves", });
    internal_static_DroneMove_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_DroneMove_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DroneMove_descriptor,
        new java.lang.String[] { "Id", "Moves", });
    internal_static_Zone_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Zone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Zone_descriptor,
        new java.lang.String[] { "Width", "Height", });
    internal_static_Message_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Message_descriptor,
        new java.lang.String[] { "MessageType", "Datas", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
