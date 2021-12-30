// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package fr.tdi.drone.common.messages;

/**
 * Protobuf type {@code Position}
 */
public  final class Position extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Position)
    PositionOrBuilder {
  // Use Position.newBuilder() to construct.
  private Position(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Position() {
    x_ = 0;
    y_ = 0;
    orientation_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Position(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            x_ = input.readInt32();
            break;
          }
          case 16: {

            y_ = input.readInt32();
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            orientation_ = rawValue;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return fr.tdi.drone.common.messages.Messages.internal_static_Position_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return fr.tdi.drone.common.messages.Messages.internal_static_Position_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            fr.tdi.drone.common.messages.Position.class, fr.tdi.drone.common.messages.Position.Builder.class);
  }

  public static final int X_FIELD_NUMBER = 1;
  private int x_;
  /**
   * <code>optional int32 x = 1;</code>
   */
  public int getX() {
    return x_;
  }

  public static final int Y_FIELD_NUMBER = 2;
  private int y_;
  /**
   * <code>optional int32 y = 2;</code>
   */
  public int getY() {
    return y_;
  }

  public static final int ORIENTATION_FIELD_NUMBER = 3;
  private int orientation_;
  /**
   * <code>optional .Orientation orientation = 3;</code>
   */
  public int getOrientationValue() {
    return orientation_;
  }
  /**
   * <code>optional .Orientation orientation = 3;</code>
   */
  public fr.tdi.drone.common.messages.Orientation getOrientation() {
    fr.tdi.drone.common.messages.Orientation result = fr.tdi.drone.common.messages.Orientation.valueOf(orientation_);
    return result == null ? fr.tdi.drone.common.messages.Orientation.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (x_ != 0) {
      output.writeInt32(1, x_);
    }
    if (y_ != 0) {
      output.writeInt32(2, y_);
    }
    if (orientation_ != fr.tdi.drone.common.messages.Orientation.NORTH.getNumber()) {
      output.writeEnum(3, orientation_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (x_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, x_);
    }
    if (y_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, y_);
    }
    if (orientation_ != fr.tdi.drone.common.messages.Orientation.NORTH.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, orientation_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof fr.tdi.drone.common.messages.Position)) {
      return super.equals(obj);
    }
    fr.tdi.drone.common.messages.Position other = (fr.tdi.drone.common.messages.Position) obj;

    boolean result = true;
    result = result && (getX()
        == other.getX());
    result = result && (getY()
        == other.getY());
    result = result && orientation_ == other.orientation_;
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + X_FIELD_NUMBER;
    hash = (53 * hash) + getX();
    hash = (37 * hash) + Y_FIELD_NUMBER;
    hash = (53 * hash) + getY();
    hash = (37 * hash) + ORIENTATION_FIELD_NUMBER;
    hash = (53 * hash) + orientation_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static fr.tdi.drone.common.messages.Position parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static fr.tdi.drone.common.messages.Position parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static fr.tdi.drone.common.messages.Position parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static fr.tdi.drone.common.messages.Position parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(fr.tdi.drone.common.messages.Position prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Position}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Position)
      fr.tdi.drone.common.messages.PositionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return fr.tdi.drone.common.messages.Messages.internal_static_Position_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return fr.tdi.drone.common.messages.Messages.internal_static_Position_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              fr.tdi.drone.common.messages.Position.class, fr.tdi.drone.common.messages.Position.Builder.class);
    }

    // Construct using fr.tdi.drone.common.messages.Position.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      x_ = 0;

      y_ = 0;

      orientation_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return fr.tdi.drone.common.messages.Messages.internal_static_Position_descriptor;
    }

    public fr.tdi.drone.common.messages.Position getDefaultInstanceForType() {
      return fr.tdi.drone.common.messages.Position.getDefaultInstance();
    }

    public fr.tdi.drone.common.messages.Position build() {
      fr.tdi.drone.common.messages.Position result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public fr.tdi.drone.common.messages.Position buildPartial() {
      fr.tdi.drone.common.messages.Position result = new fr.tdi.drone.common.messages.Position(this);
      result.x_ = x_;
      result.y_ = y_;
      result.orientation_ = orientation_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof fr.tdi.drone.common.messages.Position) {
        return mergeFrom((fr.tdi.drone.common.messages.Position)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(fr.tdi.drone.common.messages.Position other) {
      if (other == fr.tdi.drone.common.messages.Position.getDefaultInstance()) return this;
      if (other.getX() != 0) {
        setX(other.getX());
      }
      if (other.getY() != 0) {
        setY(other.getY());
      }
      if (other.orientation_ != 0) {
        setOrientationValue(other.getOrientationValue());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      fr.tdi.drone.common.messages.Position parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (fr.tdi.drone.common.messages.Position) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int x_ ;
    /**
     * <code>optional int32 x = 1;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <code>optional int32 x = 1;</code>
     */
    public Builder setX(int value) {
      
      x_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 x = 1;</code>
     */
    public Builder clearX() {
      
      x_ = 0;
      onChanged();
      return this;
    }

    private int y_ ;
    /**
     * <code>optional int32 y = 2;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <code>optional int32 y = 2;</code>
     */
    public Builder setY(int value) {
      
      y_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 y = 2;</code>
     */
    public Builder clearY() {
      
      y_ = 0;
      onChanged();
      return this;
    }

    private int orientation_ = 0;
    /**
     * <code>optional .Orientation orientation = 3;</code>
     */
    public int getOrientationValue() {
      return orientation_;
    }
    /**
     * <code>optional .Orientation orientation = 3;</code>
     */
    public Builder setOrientationValue(int value) {
      orientation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional .Orientation orientation = 3;</code>
     */
    public fr.tdi.drone.common.messages.Orientation getOrientation() {
      fr.tdi.drone.common.messages.Orientation result = fr.tdi.drone.common.messages.Orientation.valueOf(orientation_);
      return result == null ? fr.tdi.drone.common.messages.Orientation.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .Orientation orientation = 3;</code>
     */
    public Builder setOrientation(fr.tdi.drone.common.messages.Orientation value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      orientation_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .Orientation orientation = 3;</code>
     */
    public Builder clearOrientation() {
      
      orientation_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:Position)
  }

  // @@protoc_insertion_point(class_scope:Position)
  private static final fr.tdi.drone.common.messages.Position DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new fr.tdi.drone.common.messages.Position();
  }

  public static fr.tdi.drone.common.messages.Position getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Position>
      PARSER = new com.google.protobuf.AbstractParser<Position>() {
    public Position parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Position(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Position> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Position> getParserForType() {
    return PARSER;
  }

  public fr.tdi.drone.common.messages.Position getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
