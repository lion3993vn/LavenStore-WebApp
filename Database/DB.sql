USE [TheLavenStore_PRJ301]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/21/2024 11:01:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CateID] [int] IDENTITY(1,1) NOT NULL,
	[CateName] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[CateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 3/21/2024 11:01:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NULL,
	[Date] [datetime] NULL,
	[PaymentDate] [datetime] NULL,
	[PaymentMethod] [varchar](50) NULL,
	[Location] [nvarchar](255) NULL,
	[PhoneNumber] [nvarchar](20) NULL,
	[Price] [int] NULL,
	[Status] [varchar](50) NULL,
	[OrderCode] [varchar](50) NULL,
	[Note] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 3/21/2024 11:01:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[ProductID] [int] NULL,
	[Quantity] [int] NULL,
	[Price] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 3/21/2024 11:01:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[CateID] [int] NULL,
	[Quantity] [int] NULL,
	[Price] [int] NULL,
	[Rating] [float] NULL,
	[Description] [nvarchar](500) NULL,
	[Image] [varchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 3/21/2024 11:01:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](50) NULL,
	[Password] [varchar](20) NULL,
	[Email] [varchar](50) NULL,
	[Role] [varchar](20) NULL,
	[Fullname] [nvarchar](50) NULL,
	[PhoneNumber] [varchar](20) NULL,
	[Address] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Wishlist]    Script Date: 3/21/2024 11:01:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Wishlist](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (1, N'Boxes')
INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (2, N'Birthday')
INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (3, N'Party')
INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (4, N'Celebrate')
INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (5, N'Wedding')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([ID], [UserID], [Date], [PaymentDate], [PaymentMethod], [Location], [PhoneNumber], [Price], [Status], [OrderCode], [Note]) VALUES (42, 4, CAST(N'2024-03-20T00:56:00.910' AS DateTime), CAST(N'2024-03-20T00:56:45.000' AS DateTime), N'VNPAY', N'hồ chí minh', N'1231312312', 2890000, N'COMPLETED', N'98741569', N'hi')
INSERT [dbo].[Order] ([ID], [UserID], [Date], [PaymentDate], [PaymentMethod], [Location], [PhoneNumber], [Price], [Status], [OrderCode], [Note]) VALUES (43, 4, CAST(N'2024-03-20T11:15:33.897' AS DateTime), NULL, N'COD', N'hồ chí minh', N'1231312312', 1225000, N'COMPLETED', N'82488341', N'hì')
INSERT [dbo].[Order] ([ID], [UserID], [Date], [PaymentDate], [PaymentMethod], [Location], [PhoneNumber], [Price], [Status], [OrderCode], [Note]) VALUES (44, 4, CAST(N'2024-03-20T21:20:57.623' AS DateTime), NULL, N'COD', N'hồ chí minh', N'1231312312', 475000, N'PENDING', N'88734275', N'')
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (77, 42, 34, 2, 680000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (78, 42, 33, 2, 700000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (79, 42, 35, 1, 340000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (80, 42, 18, 1, 150000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (81, 42, 22, 1, 175000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (82, 42, 21, 1, 150000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (83, 42, 23, 1, 180000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (84, 42, 24, 1, 135000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (85, 42, 27, 1, 180000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (86, 42, 26, 1, 175000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (87, 43, 42, 4, 1200000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (88, 44, 18, 1, 150000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (89, 44, 19, 1, 150000)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (90, 44, 20, 1, 150000)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (17, N'Bloom', 5, 40, 120000, 4.5, N'Một bóa hoa trắng cùng bộ váy', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F1.png?alt=media&token=bd140b90-ae21-42b0-a0cd-7fc95f25b83b')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (18, N'Fashionable', 4, 28, 150000, 4, N'Bình hoa tím được trang trí vô cùng tinh tế', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F2.png?alt=media&token=7c6198fc-655c-4afc-95cf-878f87d43505')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (19, N'Exclusive', 3, 29, 150000, 5, N'Sự tinh tế và sang trọng trong từng bông hoa', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F3.png?alt=media&token=1109346f-f2bb-446a-b874-1a83c0c39331')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (20, N'Exotic', 2, 19, 150000, 4.5, N'Một đóa hoa đầy đủ màu sắc và trẻ trung', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F4.png?alt=media&token=b8f0d452-1ded-4eff-94bb-51b8174700e0')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (21, N'Roses', 2, 29, 150000, 4.5, N'Bóa hoa hồng đỏ đầy sự quyến rũ', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F5.png?alt=media&token=a9bcbf13-dc72-4b76-89ff-70a5b692800d')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (22, N'Crescent', 3, 29, 175000, 4.5, N'Sự tuyệt vời trong từng bông hoa, một bóa hoa hài hòa về màu sắc', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F6.png?alt=media&token=1e1f667e-f9e4-4283-a4b5-725f5691f105')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (23, N'Vibrant', 1, 29, 180000, 4.5, N'1 hộp hoa hồng đầy đủ màu sắc cùng chocolate tuyệt hảo', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F7.png?alt=media&token=edf0112e-8fec-4893-a80c-59c1ab08c961')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (24, N'Marvelous', 3, 39, 135000, 4.5, N'Kiểu cắm hoa thể hiện sự sang trọng và đẳng cấp', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F8.png?alt=media&token=72927a4c-973f-43ef-8308-23e64683914e')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (25, N'Elegancy', 1, 35, 190000, 5, N'Một chiếc hộp màu trắng tuyệt đẹp được trang trí bằng hoa hồng và bánh macaron', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F9.png?alt=media&token=bccaec86-cee6-42c6-8e34-04168c6f6ada')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (26, N'Flashy', 2, 44, 175000, 4.5, N'Màu vàng thể hiện sự trang trọng của hoa', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F10.png?alt=media&token=428c48f4-88f1-46d6-9442-74d28ab67b04')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (27, N'Seductive', 2, 54, 180000, 4.5, N'Với sự kết hợp quyến rũ của hoa hồng đỏ, hoa cẩm tú cầu bó hoa quyến rũ này là lời mời hoàn hảo đến với nghệ thuật quyến rũ.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F11.png?alt=media&token=c5bbe8b0-d1a9-4e40-9132-d6912b4e2a6a')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (28, N'I Love You', 1, 55, 190000, 5, N'Hộp acrylic lớn với trái tim tuyệt đẹp làm từ hoa hồng tươi', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F12.png?alt=media&token=7cf370db-f357-46e4-82e8-ab8df49d8bc4')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (29, N'Wild', 1, 0, 180000, 4, N'Một sự pha trộn độc đáo của hoa tulip trắng, tím và cam được tô điểm thêm bởi sự lựa chọn hấp dẫn của hương vani thơm ngon, quả chanh hồ trăn và bánh hạnh nhân chocolate.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F13.png?alt=media&token=59028418-e320-4ec0-8e68-7c1ce81a98e1')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (30, N'Sensation', 1, 30, 170000, 4.5, N'Một loại chocolate handmade, từ đen đến kem, được làm bằng ca cao ngon nhất cùng những bông hoa hồng cho đến tím', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F14.png?alt=media&token=6a8fa35d-c95a-43e7-99da-bef880248115')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (31, N'Grace', 1, 45, 175000, 3.5, N'1 hộp hoa tulip cùng chocolate trắng', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F15.png?alt=media&token=35b2e59d-79a5-495d-b94f-0b2e3567883f')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (32, N'Mamma Mia', 5, 45, 250000, 5, N'Đóa hoa này thể hiện sự tinh tế, sự cầu kì cùng những vẻ đẹp ẩn sâu bên trong nó', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F16.png?alt=media&token=47df7400-a0b8-412c-99a0-a63ef8ba88b0')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (33, N'Floral', 5, 3, 350000, 5, N'Đóa hoa dựa trên sự thiết kế của 1 chiếc váy trắng cùng với sự tỉ mỉ', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F17.png?alt=media&token=fb79e375-158c-4667-915c-0c7f8e4dec15')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (34, N'Floral Shoe', 5, 3, 340000, 5, N'''Kiệt tác giày'' của chúng tôi là sự cộng sinh hoàn hảo giữa thời trang và thiết kế hoa.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F18.png?alt=media&token=62dfb488-3c13-4fed-bc95-a18172260ac6')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (35, N'Ramadan', 5, 14, 340000, 5, N'''Kiệt tác'' giỏ xách này là 1 sự tinh tế, hoa mỹ với sự kết hợp tinh tế của hoa hồng và hoa hồng trắng', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F19.png?alt=media&token=cf6e6439-01af-43cd-8c40-e271feab9aa3')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (36, N'White-Pink', 3, 0, 175000, 4.5, N'Hộp hoa được trang trí bằng một bó hoa cẩm chướng. Hộp hoa là cách hoàn hảo để gửi cho ai đó một món quà đặc biệt.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F21.png?alt=media&token=250d617a-cf39-4126-8acc-1ee5b5ac2b72')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (37, N'Red Velvet', 4, 15, 180000, 4, N'Hoa hồng đựng trong hộp nhung là món quà hoàn hảo cho người yêu hoa hồng.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F22.png?alt=media&token=81ba6a2d-7883-47cf-8886-b9fe4ec39d2b')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (38, N'Spring Palette', 2, 30, 210000, 4.5, N'Một bảng màu nhẹ nhàng của hoa hồng mùa xuân kết hợp với bướm vàng hồng và hoa tulip.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F23.png?alt=media&token=423ef206-24dd-4bd5-8775-34a0793c0150')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (39, N'Fortune', 4, 5, 200000, 4.5, N'Thể hiện sự thanh lịch vượt thời gian với chiếc hộp giấy tròn quyến rũ được trang trí bằng những phụ kiện tinh xảo, những bông hoa lan tinh tế và những bông hoa màu đỏ rực rỡ
', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F24.png?alt=media&token=e84daf54-8e4d-4d82-bb1f-1dd83e6d8aee')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (40, N'Identity', 3, 20, 200000, 4.5, N'Mang cá tính vào bó hoa của bạn', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F25.png?alt=media&token=5da7d412-3cde-4966-b4e4-8671907a7a0b')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (41, N'The Gloss', 2, 30, 220000, 5, N'Được thiết kế trang nhã, bó hoa này có sự pha trộn hài hòa giữa hồng, trắng và hồng đào. Mỗi màu sắc có ý nghĩa biểu tượng riêng, bổ sung thêm nhiều tầng ý nghĩa cho sự sắp xếp trực quan ấn tượng này', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F26.png?alt=media&token=bc039b1e-44ec-4b5c-86df-7f1c05075e0c')
INSERT [dbo].[Product] ([ID], [Name], [CateID], [Quantity], [Price], [Rating], [Description], [Image]) VALUES (42, N'Very Berry', 5, 0, 300000, 4.5, N'Với sự pha trộn đầy lôi cuốn của hoa hồng, hoa lisianthus và astilbe, cùng với oxypetalum màu xanh tinh khiết thơm ngát, bó hoa ''Extravagant'' của chúng tôi sẽ khiến bạn nổi bật dưới ánh đèn sân khấu.', N'https://firebasestorage.googleapis.com/v0/b/thelavenstore-fe036.appspot.com/o/product%2F27.png?alt=media&token=74954550-9c6c-4d11-b98d-82ac8b576a80')
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([ID], [Username], [Password], [Email], [Role], [Fullname], [PhoneNumber], [Address]) VALUES (3, N'hieupvtse171097', N'123456', N'hieupvtse171097@fpt.edu.vn', N'admin', N'Híu nè', N'0974752607', N'123345')
INSERT [dbo].[Users] ([ID], [Username], [Password], [Email], [Role], [Fullname], [PhoneNumber], [Address]) VALUES (4, N'lion3993vn', N'prj301team123', N'lion3993vn@gmail.com', N'user', N'Phạm Hiếu', N'1231312312', N'hồ chí minh')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET IDENTITY_INSERT [dbo].[Wishlist] ON 

INSERT [dbo].[Wishlist] ([ID], [UserID], [ProductID]) VALUES (92, 4, 34)
INSERT [dbo].[Wishlist] ([ID], [UserID], [ProductID]) VALUES (93, 3, 29)
INSERT [dbo].[Wishlist] ([ID], [UserID], [ProductID]) VALUES (94, 3, 36)
SET IDENTITY_INSERT [dbo].[Wishlist] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__536C85E453C986BA]    Script Date: 3/21/2024 11:01:59 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([ID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([ID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([CateID])
REFERENCES [dbo].[Category] ([CateID])
GO
ALTER TABLE [dbo].[Wishlist]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[Wishlist]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([ID])
GO
