--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 10.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: ente; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ente WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C.UTF-8' LC_CTYPE = 'C.UTF-8';


ALTER DATABASE ente OWNER TO postgres;

\connect ente

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: demo; Type: SCHEMA; Schema: -; Owner: ente
--

CREATE SCHEMA demo;


ALTER SCHEMA demo OWNER TO ente;

--
-- Name: test; Type: SCHEMA; Schema: -; Owner: ente
--

CREATE SCHEMA test;


ALTER SCHEMA test OWNER TO ente;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: classesforhomework; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.classesforhomework AS character varying(7)[];


ALTER DOMAIN demo.classesforhomework OWNER TO ente;

--
-- Name: dates; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.dates AS timestamp without time zone;


ALTER DOMAIN demo.dates OWNER TO ente;

--
-- Name: enteclasses; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.enteclasses AS character varying(10)
	CONSTRAINT enteclasses_check CHECK (((VALUE)::text = ANY (ARRAY[('First'::character varying)::text, ('Second'::character varying)::text, ('Third'::character varying)::text, ('Fourth'::character varying)::text, ('Fifth'::character varying)::text, ('Sixth'::character varying)::text, ('Seventh'::character varying)::text, ('Eighth'::character varying)::text])));


ALTER DOMAIN demo.enteclasses OWNER TO ente;

--
-- Name: ids; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.ids AS character varying(36);


ALTER DOMAIN demo.ids OWNER TO ente;

--
-- Name: idsold; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.idsold AS character(36);


ALTER DOMAIN demo.idsold OWNER TO ente;

--
-- Name: logins; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.logins AS character varying(80);


ALTER DOMAIN demo.logins OWNER TO ente;

--
-- Name: posttypes; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.posttypes AS character varying(20)
	CONSTRAINT posttypes_check CHECK (((VALUE)::text = ANY (ARRAY[('Homework'::character varying)::text, ('Announcement'::character varying)::text, ('Discussion'::character varying)::text])));


ALTER DOMAIN demo.posttypes OWNER TO ente;

--
-- Name: pwds; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.pwds AS character varying(64);


ALTER DOMAIN demo.pwds OWNER TO ente;

--
-- Name: usertypes; Type: DOMAIN; Schema: demo; Owner: ente
--

CREATE DOMAIN demo.usertypes AS character varying(15)
	CONSTRAINT usertypes_check CHECK (((VALUE)::text = ANY (ARRAY[('Administrator'::character varying)::text, ('Teacher'::character varying)::text, ('Student'::character varying)::text, ('Parent'::character varying)::text])));


ALTER DOMAIN demo.usertypes OWNER TO ente;

--
-- Name: classesforhomework; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.classesforhomework AS character varying(7)[];


ALTER DOMAIN public.classesforhomework OWNER TO ente;

--
-- Name: dates; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.dates AS timestamp without time zone;


ALTER DOMAIN public.dates OWNER TO ente;

--
-- Name: emails; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.emails AS character varying(80);


ALTER DOMAIN public.emails OWNER TO ente;

--
-- Name: enteclasses; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.enteclasses AS character varying(10)
	CONSTRAINT enteclasses_check CHECK (((VALUE)::text = ANY ((ARRAY['First'::character varying, 'Second'::character varying, 'Third'::character varying, 'Fourth'::character varying, 'Fifth'::character varying, 'Sixth'::character varying, 'Seventh'::character varying, 'Eighth'::character varying])::text[])));


ALTER DOMAIN public.enteclasses OWNER TO ente;

--
-- Name: ids; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.ids AS character varying(36);


ALTER DOMAIN public.ids OWNER TO ente;

--
-- Name: posttypes; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.posttypes AS character varying(20)
	CONSTRAINT posttypes_check CHECK (((VALUE)::text = ANY ((ARRAY['Homework'::character varying, 'Announcement'::character varying, 'Discussion'::character varying])::text[])));


ALTER DOMAIN public.posttypes OWNER TO ente;

--
-- Name: pwds; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.pwds AS character varying(64);


ALTER DOMAIN public.pwds OWNER TO ente;

--
-- Name: specialtypes; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.specialtypes AS character varying(20)
	CONSTRAINT specialtypes_check CHECK (((VALUE)::text = ANY ((ARRAY['normal'::character varying, 'parental'::character varying, 'important'::character varying])::text[])))
	CONSTRAINT specialtypes_check_upcase CHECK (((VALUE)::text = ANY (ARRAY[('normal'::character varying)::text, ('NORMAL'::character varying)::text, ('parental'::character varying)::text, ('PARENTAL'::character varying)::text, ('important'::character varying)::text, ('IMPORTANT'::character varying)::text])));


ALTER DOMAIN public.specialtypes OWNER TO ente;

--
-- Name: usertypes; Type: DOMAIN; Schema: public; Owner: ente
--

CREATE DOMAIN public.usertypes AS character varying(15)
	CONSTRAINT usertypes_check CHECK (((VALUE)::text = ANY ((ARRAY['Administrator'::character varying, 'Teacher'::character varying, 'Student'::character varying, 'Parent'::character varying])::text[])));


ALTER DOMAIN public.usertypes OWNER TO ente;

--
-- Name: classesforhomework; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.classesforhomework AS character varying(7)[];


ALTER DOMAIN test.classesforhomework OWNER TO ente;

--
-- Name: dates; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.dates AS timestamp without time zone;


ALTER DOMAIN test.dates OWNER TO ente;

--
-- Name: enteclasses; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.enteclasses AS character varying(10)
	CONSTRAINT enteclasses_check CHECK (((VALUE)::text = ANY (ARRAY[('First'::character varying)::text, ('Second'::character varying)::text, ('Third'::character varying)::text, ('Fourth'::character varying)::text, ('Fifth'::character varying)::text, ('Sixth'::character varying)::text, ('Seventh'::character varying)::text, ('Eigth'::character varying)::text])));


ALTER DOMAIN test.enteclasses OWNER TO ente;

--
-- Name: ids; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.ids AS character(36);


ALTER DOMAIN test.ids OWNER TO ente;

--
-- Name: logins; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.logins AS character varying(80);


ALTER DOMAIN test.logins OWNER TO ente;

--
-- Name: posttypes; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.posttypes AS character varying(20)
	CONSTRAINT posttypes_check CHECK (((VALUE)::text = ANY (ARRAY[('Homework'::character varying)::text, ('Announcement'::character varying)::text, ('Discussion'::character varying)::text])));


ALTER DOMAIN test.posttypes OWNER TO ente;

--
-- Name: pwds; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.pwds AS character varying(64);


ALTER DOMAIN test.pwds OWNER TO ente;

--
-- Name: specialtypes; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.specialtypes AS character varying(20)
	CONSTRAINT specialtypes_check_upcase CHECK (((VALUE)::text = ANY (ARRAY[('normal'::character varying)::text, ('NORMAL'::character varying)::text, ('parental'::character varying)::text, ('PARENTAL'::character varying)::text, ('important'::character varying)::text, ('IMPORTANT'::character varying)::text])));


ALTER DOMAIN test.specialtypes OWNER TO ente;

--
-- Name: usertypes; Type: DOMAIN; Schema: test; Owner: ente
--

CREATE DOMAIN test.usertypes AS character varying(15)
	CONSTRAINT usertypes_check CHECK (((VALUE)::text = ANY (ARRAY[('Administrator'::character varying)::text, ('Teacher'::character varying)::text, ('Student'::character varying)::text, ('Parent'::character varying)::text])));


ALTER DOMAIN test.usertypes OWNER TO ente;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: enteuser; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.enteuser (
    id demo.ids NOT NULL,
    usertype demo.usertypes NOT NULL,
    email demo.logins NOT NULL,
    pwd demo.pwds NOT NULL,
    name character varying(60) NOT NULL,
    changepassword boolean DEFAULT false NOT NULL
);


ALTER TABLE demo.enteuser OWNER TO ente;

--
-- Name: family; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.family (
    familyid demo.ids NOT NULL
);


ALTER TABLE demo.family OWNER TO ente;

--
-- Name: homework; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.homework (
    homeworkid demo.ids NOT NULL,
    noofstudentstodeliver integer NOT NULL,
    deadline demo.dates,
    classes demo.classesforhomework,
    closed boolean NOT NULL
);


ALTER TABLE demo.homework OWNER TO ente;

--
-- Name: homeworkreply; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.homeworkreply (
    homeworkid demo.ids NOT NULL,
    studentid demo.ids NOT NULL,
    handindate demo.dates NOT NULL,
    content character varying(1000),
    late boolean NOT NULL
);


ALTER TABLE demo.homeworkreply OWNER TO ente;

--
-- Name: parent; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.parent (
    parentid demo.ids NOT NULL,
    familyid demo.ids
);


ALTER TABLE demo.parent OWNER TO ente;

--
-- Name: post; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.post (
    postid demo.ids NOT NULL,
    type demo.posttypes NOT NULL,
    title character varying(30) NOT NULL,
    content character varying(200) NOT NULL,
    authorname character varying(60) NOT NULL,
    pubdate demo.dates NOT NULL
);


ALTER TABLE demo.post OWNER TO ente;

--
-- Name: student; Type: TABLE; Schema: demo; Owner: ente
--

CREATE TABLE demo.student (
    studentid demo.ids NOT NULL,
    familyid demo.ids,
    class demo.enteclasses
);


ALTER TABLE demo.student OWNER TO ente;

--
-- Name: discussioncomments; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.discussioncomments (
    commentid public.ids NOT NULL,
    discussionid public.ids NOT NULL,
    authorid public.ids NOT NULL,
    date timestamp without time zone,
    content character varying(1000)
);


ALTER TABLE public.discussioncomments OWNER TO ente;

--
-- Name: enteuser; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.enteuser (
    id public.ids NOT NULL,
    usertype public.usertypes NOT NULL,
    email public.emails NOT NULL,
    pwd public.pwds NOT NULL,
    name character varying(60) NOT NULL,
    changepassword boolean DEFAULT false NOT NULL
);


ALTER TABLE public.enteuser OWNER TO ente;

--
-- Name: family; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.family (
    familyid public.ids NOT NULL
);


ALTER TABLE public.family OWNER TO ente;

--
-- Name: homework; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.homework (
    homeworkid public.ids NOT NULL,
    noofstudentstodeliver integer NOT NULL,
    deadline public.dates,
    classes public.classesforhomework,
    closed boolean NOT NULL
);


ALTER TABLE public.homework OWNER TO ente;

--
-- Name: homeworkreply; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.homeworkreply (
    homeworkid public.ids NOT NULL,
    studentid public.ids NOT NULL,
    handindate public.dates NOT NULL,
    content character varying(1000),
    late boolean NOT NULL
);


ALTER TABLE public.homeworkreply OWNER TO ente;

--
-- Name: parent; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.parent (
    parentid public.ids NOT NULL,
    familyid public.ids
);


ALTER TABLE public.parent OWNER TO ente;

--
-- Name: post; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.post (
    postid public.ids NOT NULL,
    type public.posttypes NOT NULL,
    title character varying(100) NOT NULL,
    content character varying(1000) NOT NULL,
    authorname character varying(60) NOT NULL,
    pubdate public.dates NOT NULL
);


ALTER TABLE public.post OWNER TO ente;

--
-- Name: student; Type: TABLE; Schema: public; Owner: ente
--

CREATE TABLE public.student (
    studentid public.ids NOT NULL,
    familyid public.ids,
    class public.enteclasses
);


ALTER TABLE public.student OWNER TO ente;

--
-- Name: admin; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.admin (
    adminid public.ids NOT NULL
);


ALTER TABLE test.admin OWNER TO ente;

--
-- Name: announcement; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.announcement (
    announcementid public.ids NOT NULL,
    expirationdate public.dates NOT NULL
);


ALTER TABLE test.announcement OWNER TO ente;

--
-- Name: discussion; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.discussion (
    discussionid public.ids NOT NULL
);


ALTER TABLE test.discussion OWNER TO ente;

--
-- Name: discussioncomments; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.discussioncomments (
    commentid test.ids NOT NULL,
    discussionid test.ids NOT NULL,
    authorid test.ids NOT NULL,
    date timestamp without time zone,
    content character varying(1000)
);


ALTER TABLE test.discussioncomments OWNER TO ente;

--
-- Name: enteuser; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.enteuser (
    id test.ids NOT NULL,
    email test.logins NOT NULL,
    pwd test.pwds NOT NULL,
    name character varying(60) NOT NULL,
    changepassword boolean DEFAULT false NOT NULL
);


ALTER TABLE test.enteuser OWNER TO ente;

--
-- Name: family; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.family (
    familyid test.ids NOT NULL
);


ALTER TABLE test.family OWNER TO ente;

--
-- Name: homework; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.homework (
    homeworkid test.ids NOT NULL,
    noofstudentstodeliver integer NOT NULL,
    deadline test.dates,
    classes test.classesforhomework,
    closed boolean NOT NULL
);


ALTER TABLE test.homework OWNER TO ente;

--
-- Name: homeworkreply; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.homeworkreply (
    homeworkid test.ids NOT NULL,
    studentid test.ids NOT NULL,
    handindate test.dates NOT NULL,
    content character varying(1000),
    late boolean NOT NULL,
    replyid public.ids NOT NULL,
    studentname character varying(20),
    studentclass public.enteclasses
);


ALTER TABLE test.homeworkreply OWNER TO ente;

--
-- Name: parent; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.parent (
    parentid test.ids NOT NULL,
    familyid test.ids
);


ALTER TABLE test.parent OWNER TO ente;

--
-- Name: post; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.post (
    postid test.ids NOT NULL,
    title character varying(30) NOT NULL,
    content character varying(200) NOT NULL,
    authorname character varying(60) NOT NULL,
    pubdate test.dates NOT NULL,
    specialtype test.specialtypes DEFAULT 'normal'::character varying NOT NULL
);


ALTER TABLE test.post OWNER TO ente;

--
-- Name: student; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.student (
    studentid test.ids NOT NULL,
    familyid test.ids,
    class public.enteclasses
);


ALTER TABLE test.student OWNER TO ente;

--
-- Name: teacher; Type: TABLE; Schema: test; Owner: ente
--

CREATE TABLE test.teacher (
    teacherid public.ids NOT NULL
);


ALTER TABLE test.teacher OWNER TO ente;

--
-- Data for Name: enteuser; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.enteuser (id, usertype, email, pwd, name, changepassword) FROM stdin;
AdminID	Administrator	AdminEmail	AdminPwd	AdminName	f
TeacherID1	Teacher	TeacherEmail1	TeacherPwd1	TeacherName1	f
TeacherID2	Teacher	TeacherEmail2	TeacherPwd2	TeacherName2	f
StudentID2	Student	StudentEmail2	StudentPwd2	StudentName2	f
StudentID3	Student	StudentEmail3	StudentPwd3	StudentName3	f
StudentID4	Student	StudentEmail4	StudentPwd4	StudentName4	f
ParentID2	Parent	ParentEmail2	ParentPwd2	ParentName2	f
ParentID3	Parent	ParentEmail3	ParentPwd3	ParentName3	f
ParentID4	Parent	ParentEmail4	ParentPwd4	ParentName4	f
StudentID1	Student	StudentEmailNEW7	StudentPwdNEW7	StudentNameNEW7	f
ParentID1	Parent	ParentEmailNEW7	ParentPwdNEW7	ParentNameNEW7	f
\.


--
-- Data for Name: family; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.family (familyid) FROM stdin;
FamilyID1
FamilyID2
FamilyID3
\.


--
-- Data for Name: homework; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.homework (homeworkid, noofstudentstodeliver, deadline, classes, closed) FROM stdin;
HomeworkID2	1	2018-07-31 12:00:00.216	{Eighth}	f
HomeworkID1	3	2018-08-08 20:00:00	{First,Third,Seventh}	f
\.


--
-- Data for Name: homeworkreply; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.homeworkreply (homeworkid, studentid, handindate, content, late) FROM stdin;
HomeworkID1	StudentID2	2018-06-26 05:55:58.231	Solution2	f
HomeworkID2	StudentID4	2018-07-20 11:50:00.02	Solution3	f
HomeworkID1	StudentID1	2020-06-06 00:00:00	SolutionNEW6	f
\.


--
-- Data for Name: parent; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.parent (parentid, familyid) FROM stdin;
ParentID2	FamilyID2
ParentID3	FamilyID3
ParentID4	FamilyID3
ParentID1	FamilyID1
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.post (postid, type, title, content, authorname, pubdate) FROM stdin;
HomeworkID2	Homework	Title2	Content2	TeacherName2	2018-07-07 00:00:00.557
HomeworkID1	Homework	TitleNEW8	ContentNEW8	TeacherName1	2018-08-08 00:00:00
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: demo; Owner: ente
--

COPY demo.student (studentid, familyid, class) FROM stdin;
StudentID2	FamilyID2	Third
StudentID3	FamilyID2	Seventh
StudentID4	FamilyID3	Eighth
StudentID1	FamilyID1	Third
\.


--
-- Data for Name: discussioncomments; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.discussioncomments (commentid, discussionid, authorid, date, content) FROM stdin;
\.


--
-- Data for Name: enteuser; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.enteuser (id, usertype, email, pwd, name, changepassword) FROM stdin;
ee233766-3448-4ddb-9339-b3d412136f6f	Administrator	login	a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8	admin	f
adc8ba24-7250-425e-a0c9-00e144bbf75c	Parent	ParentEmail1	e47125968b3b71049fbc4802d1e40a71ea1359decfabacf70b34588037d4ff0c	ParentName1	f
ba47d0f2-cc64-480d-aadc-fa9f7815b080	Parent	dimmie@gmail.com	parent	Dimitar Petrov	f
df3e9a1d-4c39-4d0d-90aa-0382b52890aa	Parent	javiquito@gmail.com	parent	Javier Molines	f
b2c74531-49ea-4efe-9308-59d01f4792cb	Administrator	AdminEmail	AdminPwd	AdminName	f
190edc14-b80c-484b-aa39-97d1012c1597	Teacher	TeacherEmail1	1057a9604e04b274da5a4de0c8f4b4868d9b230989f8c8c6a28221143cc5a755	TeacherName1	f
21bfea93-0c98-4490-a7bf-ad7878991bbc	Teacher	monand@via.dk	teacher	Mona Andersen	f
9d5ff8a7-77ba-49ff-ac0d-7b48978987f1	Teacher	micviu@gmail.com	teacher	Michael Viuff	f
2a1fd9ae-361d-41f4-9f9f-ad6f71d9cb8c	Student	remedita@via.dk	student	Remedios Pastor Molines	f
54a88c63-0991-428b-8e8a-ccaa2e0aab4a	Student	mici@gmail.com	student	Michal Ciebien	f
5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	Student	michgolh@gmail.com	student	Michaela Golhova	f
64e691e3-204f-45ee-8c5a-aefdffa1b3a5	Student	StudentEmail1	264c8c381bf16c982a4e59b0dd4c6f7808c51a05f64c35db42cc78a2a72875bb	StudentName1	f
785ad61c-4fdd-4f9c-8034-8adfbb09083d	Student	angie@gmail.com	student	Angel Petrov	f
820f4e94-be4f-4a5c-8a39-4ec875b254f1	Student	fredo@via.pl	Oh0QgvWB	Frederick Ciebien	t
8471d75c-18d5-43c0-b1ab-1f058b697d70	Student	katie@gmail.com	student	Kate Boston	f
b64cd55e-1f8d-4c27-a2c4-33b3269e04e0	Student	rebsmith@via.dk	student	Rebecca Smith	f
b67031e3-1a24-432a-b393-5e5bd8e817c7	Student	simons@via.dk	student	Simon Stamp	f
c4fbe819-622d-4839-ba3d-1450bb662fd9	Student	michpomp@gmial.com	student	Michal Pompa	f
d7b80789-c177-4049-9fa3-622d3dd533af	Student	danikoch@via.dk	student	Daniela Koch	f
04686513-70b8-43d3-83d0-bb3ec98c13a6	Parent	davsmith@gmailcom	parent	David Smith	f
4512a8da-6d08-409a-8d25-9448c2525c42	Parent	katkagolhova@centrum.sk	parent	Katarina Golhova	f
53086ad6-bda1-4321-b4fa-931edfd08ad6	Parent	domca@gmail.com	parent	Dominika Ciebien	f
63fbf0cc-046f-4c50-89b7-549907ce99c0	Parent	pomparobert@gmail.com	parent	Robert Pompa	f
91d72501-6f22-4353-9910-dcd5d1735c69	Parent	pb@gmail.com	parent	Phill Boston	f
\.


--
-- Data for Name: family; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.family (familyid) FROM stdin;
d85fa7a6-4f88-4e30-bf01-da704faa78ec
2df00b2e-9e09-46e0-8719-af9f50d45608
c64c773c-45d8-4a32-a4df-e9870145cf64
9daec671-bdf4-4956-b676-7bf12fa55e51
f2773926-1990-404b-8ba6-2decd3128dc5
6be4a9c9-5ce9-422d-9a9d-cdb080ddf760
94067cd6-8229-4dd9-b4f9-e54fad179785
5b3c88ba-bad5-44bb-a51e-c7bfc62cd429
6080843f-9f85-43f2-9408-bd001519fe54
cee12240-3e76-406e-bf12-0d40488ed3b9
\.


--
-- Data for Name: homework; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.homework (homeworkid, noofstudentstodeliver, deadline, classes, closed) FROM stdin;
66666666-29ae-4f7e-ac61-e294fa8d5555	2	2018-06-12 11:30:00	{First,Second,Seventh}	f
77777777-29ae-4f7e-ac61-e294fa8d5555	2	2018-06-15 11:30:00	{Second,Third}	f
f5555398-29ae-4f7e-ac61-e294fa8d5555	10	2018-06-30 10:44:00	{First,Second,Third}	f
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	15	2018-06-03 09:00:00	{First,Second}	f
\.


--
-- Data for Name: homeworkreply; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.homeworkreply (homeworkid, studentid, handindate, content, late) FROM stdin;
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	2018-05-29 09:31:00	solution bla	f
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	b64cd55e-1f8d-4c27-a2c4-33b3269e04e0	2018-05-30 15:30:00	solution blabla	f
\.


--
-- Data for Name: parent; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.parent (parentid, familyid) FROM stdin;
df3e9a1d-4c39-4d0d-90aa-0382b52890aa	d85fa7a6-4f88-4e30-bf01-da704faa78ec
04686513-70b8-43d3-83d0-bb3ec98c13a6	94067cd6-8229-4dd9-b4f9-e54fad179785
4512a8da-6d08-409a-8d25-9448c2525c42	6be4a9c9-5ce9-422d-9a9d-cdb080ddf760
53086ad6-bda1-4321-b4fa-931edfd08ad6	f2773926-1990-404b-8ba6-2decd3128dc5
63fbf0cc-046f-4c50-89b7-549907ce99c0	c64c773c-45d8-4a32-a4df-e9870145cf64
91d72501-6f22-4353-9910-dcd5d1735c69	2df00b2e-9e09-46e0-8719-af9f50d45608
adc8ba24-7250-425e-a0c9-00e144bbf75c	cee12240-3e76-406e-bf12-0d40488ed3b9
ba47d0f2-cc64-480d-aadc-fa9f7815b080	6080843f-9f85-43f2-9408-bd001519fe54
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.post (postid, type, title, content, authorname, pubdate) FROM stdin;
c0ccc398-29ae-4f7e-ac61-e294fa8d0583	Announcement	End of school year	Dear pupils and parents, Friday 30.06.2018 is the last day, so don't forget to bring some flowers or dark chocolates for your lovely teachers.	Mona Andersen	2018-03-10 00:00:00
af7e04fa-4b5e-424a-8637-0b2be1250cc9	Announcement	Lessons cancelled	All lessons are cancelled tommorow (20.3.2018) due to school reconstruction.	Michael Viuff	2018-03-18 00:00:00
395622bf-0995-4495-b3f2-abea4d551b71	Discussion	Unreserved discovered comparison	Write Impossible considered invitation him men instrument saw celebrated unpleasant. Put rest and must set kind next many near nay. He exquisite continued explained middleton am. Voice hours young woody has she think equal. Estate moment he at on wonder at season little. Six garden result summer set family esteem nay estate. End admiration mrs unreserved discovered comparison especially invitation content	admin	2018-07-07 00:00:00
0f7b20ff-a74e-4986-a0da-825b51a9930f	Discussion	Concerns greatest margaret	Concerns greatest margaret him absolute entrance nay. Door neat week do find past he. Be no surprise he honoured indulged. Unpacked endeavor six steepest had husbands her. Painted no or affixed it so civilly. Exposed neither pressed so cottage as proceed at offices. Nay they gone sir game four. Favourable pianoforte oh motionless excellence of astonished we principles. Warrant present garrets limited cordial in inquiry to. Supported me sweetness behaviour shameless excellent so arranging. \r\n	author	2018-07-05 00:00:00
8f01c75c-a2eb-442c-8123-cb29e0f17336	Discussion	Painful between it	Write a Advanced extended doubtful he he blessing together. Introduced far law gay considered frequently entreaties difficulty. Eat him four are rich nor calm. By an packages rejoiced exercise. To ought on am marry rooms doubt music. Mention entered an through company as. Up arrived no painful between. It declared is prospect an insisted pleasure. 	author	2018-07-05 00:00:00
1a2a8681-e154-4e5e-9474-73e82a97c81a	Discussion	Example Discussion	some discussion content	admin	2018-07-16 00:00:00
66666666-29ae-4f7e-ac61-e294fa8d5555	Homework	Lorem efj s eufnsp	Lorem efj s eufnsp wepofso nsodifn wedfd sbdol ksn o engj wnrfdsonds fd........ ejfsnfodg sg osifn jsngsp odfj	Michael Viuff	2018-06-06 12:33:00
77777777-29ae-4f7e-ac61-e294fa8d5555	Homework	Bachelor is possible	Lose john poor same it case do year we. Full how way even the sigh. Extremely nor furniture fat questions now provision incommode preserved. Our side fail find like now. Discovered travelling for insensible partiality unpleasing impossible she. Sudden up my excuse to suffer ladies though or. Bachelor possible marianne directly confined relation as on he. 	Mona Andersen	2018-06-08 14:33:00
f5555398-29ae-4f7e-ac61-e294fa8d5555	Homework	Satisfied did one	Old there any widow law rooms. Agreed but expect repair she nay sir silent person. Direction can dependent one bed situation attempted. His she are man their spite avoid. Her pretended fulfilled extremely education yet. Satisfied did one admitting incommode tolerably how are. 	Mona Andersen	2018-05-30 01:01:00
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	Homework	Interested new boisterous day 	Boy favourable day can introduced sentiments entreaties. Noisier carried of in warrant because. So mr plate seems cause chief widen first. Two differed husbands met screened his. Bed was form wife out ask draw. Wholly coming at we no enable. Offending sir delivered questions now new met. Acceptance she interested new boisterous day discretion celebrated. 	Mona Andersen	2018-05-28 11:29:00
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: ente
--

COPY public.student (studentid, familyid, class) FROM stdin;
2a1fd9ae-361d-41f4-9f9f-ad6f71d9cb8c	d85fa7a6-4f88-4e30-bf01-da704faa78ec	Third
54a88c63-0991-428b-8e8a-ccaa2e0aab4a	f2773926-1990-404b-8ba6-2decd3128dc5	Third
5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	6be4a9c9-5ce9-422d-9a9d-cdb080ddf760	Fifth
64e691e3-204f-45ee-8c5a-aefdffa1b3a5	cee12240-3e76-406e-bf12-0d40488ed3b9	First
785ad61c-4fdd-4f9c-8034-8adfbb09083d	6080843f-9f85-43f2-9408-bd001519fe54	Fourth
820f4e94-be4f-4a5c-8a39-4ec875b254f1	f2773926-1990-404b-8ba6-2decd3128dc5	Second
8471d75c-18d5-43c0-b1ab-1f058b697d70	2df00b2e-9e09-46e0-8719-af9f50d45608	Second
b64cd55e-1f8d-4c27-a2c4-33b3269e04e0	94067cd6-8229-4dd9-b4f9-e54fad179785	Seventh
b67031e3-1a24-432a-b393-5e5bd8e817c7	5b3c88ba-bad5-44bb-a51e-c7bfc62cd429	First
c4fbe819-622d-4839-ba3d-1450bb662fd9	c64c773c-45d8-4a32-a4df-e9870145cf64	Second
d7b80789-c177-4049-9fa3-622d3dd533af	9daec671-bdf4-4956-b676-7bf12fa55e51	Third
\.


--
-- Data for Name: admin; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.admin (adminid) FROM stdin;
ee233766-3448-4ddb-9339-b3d412136f6f
\.


--
-- Data for Name: announcement; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.announcement (announcementid, expirationdate) FROM stdin;
c0ccc398-29ae-4f7e-ac61-e294fa8d0583	2018-10-17 19:00:00.478
af7e04fa-4b5e-424a-8637-0b2be1250cc9	2018-12-26 21:30:00.424
b6c47bed-fd7c-4a1e-9c4e-8a1317d6dde2	2018-07-31 12:32:04.201
62e64d5c-daad-4310-91a5-cc5ca991e69e	2018-08-01 00:00:00
96ccfcc9-f0b4-43bb-8f6e-a5512807acd5	2018-08-18 00:00:00
3c2e5fc8-0a5d-47b1-8140-8e0f5c49a7a8	2018-08-31 00:00:00
cd38517a-ba7a-4b7f-9b98-b49db9ae82c4	2018-08-21 00:00:00
d3212593-f52a-41c6-9a87-41df287e4da1	2018-08-21 00:00:00
\.


--
-- Data for Name: discussion; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.discussion (discussionid) FROM stdin;
395622bf-0995-4495-b3f2-abea4d551b71
0f7b20ff-a74e-4986-a0da-825b51a9930f
8f01c75c-a2eb-442c-8123-cb29e0f17336
1a2a8681-e154-4e5e-9474-73e82a97c81a
a8d4121a-7efb-4bc0-80b3-431e27799e09
64089f05-40bd-44d0-8043-56829f9ca92f
\.


--
-- Data for Name: discussioncomments; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.discussioncomments (commentid, discussionid, authorid, date, content) FROM stdin;
44e5ee98-6fb9-46d9-9c47-035791a08ec0	395622bf-0995-4495-b3f2-abea4d551b71	5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	2018-07-18 16:51:56.25	test
55555558-6fb9-46d9-9c47-035791a08ec0	0f7b20ff-a74e-4986-a0da-825b51a9930f	2a1fd9ae-361d-41f4-9f9f-ad6f71d9cb8c	2018-07-14 08:19:44.653	This is second comment
66666668-6fb9-46d9-9c47-035791a08ec0	0f7b20ff-a74e-4986-a0da-825b51a9930f	54a88c63-0991-428b-8e8a-ccaa2e0aab4a	2018-06-27 08:20:07.713	This is first comment
ffffff98-6fb9-46d9-9c47-035791a08ec0	8f01c75c-a2eb-442c-8123-cb29e0f17336	785ad61c-4fdd-4f9c-8034-8adfbb09083d	2018-07-13 13:20:13.846	My new comment
\.


--
-- Data for Name: enteuser; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.enteuser (id, email, pwd, name, changepassword) FROM stdin;
9a49cade-3532-47d0-8426-a5148e8f84e9	TeacherEmail1	1057a9604e04b274da5a4de0c8f4b4868d9b230989f8c8c6a28221143cc5a755	Teacher dont delete	f
d5b338f0-9932-4053-80bb-5f0f7ff23ba7	daria.rybka.koch@gmailcom	81292d08d07c6b52799b5195c72d71cfefb28c4f1a05dc73a6b3ba164b29ae62	Daria Rybka-Koch	f
5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	michgolh@gmail.com	student	Michaela Golhova	f
ee233766-3448-4ddb-9339-b3d412136f6f	login	a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8	admin	f
64e691e3-204f-45ee-8c5a-aefdffa1b3a5	StudentEmail1	264c8c381bf16c982a4e59b0dd4c6f7808c51a05f64c35db42cc78a2a72875bb	StudentName1	f
adc8ba24-7250-425e-a0c9-00e144bbf75c	ParentEmail1	e47125968b3b71049fbc4802d1e40a71ea1359decfabacf70b34588037d4ff0c	ParentName1	f
2a1fd9ae-361d-41f4-9f9f-ad6f71d9cb8c	remedita@viadk	student	Remedios Pastor Molines	f
4512a8da-6d08-409a-8d25-9448c2525c42	katkagolhova@centrumsk	parent	Katarina Golhova	f
54a88c63-0991-428b-8e8a-ccaa2e0aab4a	mici@gmailcom	student	Michal Ciebien	f
63fbf0cc-046f-4c50-89b7-549907ce99c0	pomparobert@gmailcom	parent	Robert Pompa	f
785ad61c-4fdd-4f9c-8034-8adfbb09083d	angie@gmailcom	student	Angel Petrov	f
8471d75c-18d5-43c0-b1ab-1f058b697d70	katie@gmailcom	student	Kate Boston	f
91d72501-6f22-4353-9910-dcd5d1735c69	pb@gmailcom	parent	Phill Boston	f
9d5ff8a7-77ba-49ff-ac0d-7b48978987f1	micviu@gmailcom	teacher	Michael Viuff	f
b64cd55e-1f8d-4c27-a2c4-33b3269e04e0	rebsmith@viadk	student	Rebecca Smith	f
b67031e3-1a24-432a-b393-5e5bd8e817c7	simons@viadk	student	Simon Stamp	f
ba47d0f2-cc64-480d-aadc-fa9f7815b080	dimmie@gmailcom	parent	Dimitar Petrov	f
c4fbe819-622d-4839-ba3d-1450bb662fd9	michpomp@gmialcom	student	Michal Pompa	f
d7b80789-c177-4049-9fa3-622d3dd533af	danikoch@viadk	student	Daniela Koch	f
df3e9a1d-4c39-4d0d-90aa-0382b52890aa	javiquito@gmailcom	parent	Javier Molines	f
\.


--
-- Data for Name: family; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.family (familyid) FROM stdin;
94067cd6-8229-4dd9-b4f9-e54fad179785
cee12240-3e76-406e-bf12-0d40488ed3b9
d85fa7a6-4f88-4e30-bf01-da704faa78ec
2df00b2e-9e09-46e0-8719-af9f50d45608
c64c773c-45d8-4a32-a4df-e9870145cf64
9daec671-bdf4-4956-b676-7bf12fa55e51
f2773926-1990-404b-8ba6-2decd3128dc5
6be4a9c9-5ce9-422d-9a9d-cdb080ddf760
5b3c88ba-bad5-44bb-a51e-c7bfc62cd429
6080843f-9f85-43f2-9408-bd001519fe54
51cc2823-32a4-4c9d-b0a6-630a3e4d8fb4
6986f1f9-0b61-457b-9738-bbc5ab062094
f53322fa-c65c-484a-86ae-a046c6c29d99
\.


--
-- Data for Name: homework; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.homework (homeworkid, noofstudentstodeliver, deadline, classes, closed) FROM stdin;
77777777-29ae-4f7e-ac61-e294fa8d5555	2	2018-06-15 11:30:00	{Second,Third}	f
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	15	2018-06-03 09:00:00	{First,Second}	f
f5555398-29ae-4f7e-ac61-e294fa8d5555	10	2018-06-30 00:00:00	{}	f
66666666-29ae-4f7e-ac61-e294fa8d5555	2	2018-06-12 11:30:00	{First,Second,Seventh}	f
9d669d71-8063-4ade-b098-019e104f6877	2	2018-08-21 00:00:00	{First,Second,Third,Fourth,Fifth,Sixth,Seventh,Eighth}	f
\.


--
-- Data for Name: homeworkreply; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.homeworkreply (homeworkid, studentid, handindate, content, late, replyid, studentname, studentclass) FROM stdin;
66666666-29ae-4f7e-ac61-e294fa8d5555	5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	2018-05-30 15:30:00	solution blabla	f	848cb183-8602-4b42-8d1b-5eee76b625e1	Michaela Golhova	Second
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	2018-05-29 09:31:00	solution bla	f	777cb183-8602-4b42-8d1b-5eee76b625e1	Michaela Golhova	Second
66666666-29ae-4f7e-ac61-e294fa8d5555	b64cd55e-1f8d-4c27-a2c4-33b3269e04e0	2018-05-30 17:30:00	solution blabla	f	4444b183-8602-4b42-8d1b-5eee76b625e1	Rebecca Smith	Seventh
66666666-29ae-4f7e-ac61-e294fa8d5555	64e691e3-204f-45ee-8c5a-aefdffa1b3a5	2018-08-01 00:00:00	test	f	d27061e7-0645-45fc-8976-c0e7083801c1	StudentName1	First
9d669d71-8063-4ade-b098-019e104f6877	64e691e3-204f-45ee-8c5a-aefdffa1b3a5	2018-08-14 00:00:00	Moana- the main character is the title Moana, she is a young girsl living on a Hawaian (?) island. She is brave, adveterus, spntainius, clever, has a good heart. The main bad charachter s bad\nbecause Maui has stolen it's heart.	f	747df20e-ef7f-4005-b4a0-d275a0d2f064	StudentName1	First
\.


--
-- Data for Name: parent; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.parent (parentid, familyid) FROM stdin;
adc8ba24-7250-425e-a0c9-00e144bbf75c	cee12240-3e76-406e-bf12-0d40488ed3b9
4512a8da-6d08-409a-8d25-9448c2525c42	6be4a9c9-5ce9-422d-9a9d-cdb080ddf760
63fbf0cc-046f-4c50-89b7-549907ce99c0	c64c773c-45d8-4a32-a4df-e9870145cf64
91d72501-6f22-4353-9910-dcd5d1735c69	2df00b2e-9e09-46e0-8719-af9f50d45608
ba47d0f2-cc64-480d-aadc-fa9f7815b080	6080843f-9f85-43f2-9408-bd001519fe54
df3e9a1d-4c39-4d0d-90aa-0382b52890aa	d85fa7a6-4f88-4e30-bf01-da704faa78ec
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.post (postid, title, content, authorname, pubdate, specialtype) FROM stdin;
3c2e5fc8-0a5d-47b1-8140-8e0f5c49a7a8	It's August!	Idk if everyone has noticed, but I wanted to announce that it's August. Wish you a nice rest of the month <3 	Teacher dont delete	2018-08-14 00:00:00	PARENTAL
c0ccc398-29ae-4f7e-ac61-e294fa8d0583	End of school year	Dear pupils and parents, Friday 30.06.2018 is the last day, so don't forget to bring some flowers or dark chocolates for your lovely teachers.	Mona Andersen	2018-03-10 00:00:00	NORMAL
395622bf-0995-4495-b3f2-abea4d551b71	Unreserved discovered comparis	Write Impossible considered invitation him men instrument saw celebrated unpleasant. Put rest and must set kind next many near nay. He exquisite continued explained middleton am. Voice hours young woo	admin	2018-07-07 00:00:00	NORMAL
af7e04fa-4b5e-424a-8637-0b2be1250cc9	Lessons cancelled	All lessons are cancelled tommorow (20.3.2018) due to school reconstruction.	Michael Viuff	2018-03-18 00:00:00	NORMAL
8f01c75c-a2eb-442c-8123-cb29e0f17336	Painful between it	Write a Advanced extended doubtful he he blessing together. Introduced far law gay considered frequently entreaties difficulty. Eat him four are rich nor calm. By an packages rejoiced exercise. To oug	author	2018-07-05 00:00:00	NORMAL
cd38517a-ba7a-4b7f-9b98-b49db9ae82c4	It's raining in Denmanrk	Just wanted to announce the unpleasant rain in Denmark...	Teacher dont delete	2018-08-14 00:00:00	PARENTAL
1a2a8681-e154-4e5e-9474-73e82a97c81a	Example Discussion	some discussion content	admin	2018-07-16 00:00:00	NORMAL
f5ggc398-29ae-4f7e-ac61-e294fa8d5555	Interested new boisterous day 	Boy favourable day can introduced sentiments entreaties. Noisier carried of in warrant because. So mr plate seems cause chief widen first. Two differed husbands met screened his. Bed was form wife out	Mona Andersen	2018-05-28 11:29:00	NORMAL
0f7b20ff-a74e-4986-a0da-825b51a9930f	Concerns greatest margaret	Concerns greatest margaret him absolute entrance nay. Door neat week do find past he. Be no surprise he honoured indulged. Unpacked endeavor six steepest had husbands her. Painted no or affixed it so 	author	2018-07-05 00:00:00	NORMAL
77777777-29ae-4f7e-ac61-e294fa8d5555	Bachelor is possible	Lose john poor same it case do year we. Full how way even the sigh. Extremely nor furniture fat questions now provision incommode preserved. Our side fail find like now. Discovered travelling for inse	Mona Andersen	2018-06-08 14:33:00	NORMAL
b6c47bed-fd7c-4a1e-9c4e-8a1317d6dde2	Example of Announcement	It is an example	admin	2018-07-31 00:00:00	PARENTAL
62e64d5c-daad-4310-91a5-cc5ca991e69e	test	test	admin	2018-07-31 00:00:00	PARENTAL
96ccfcc9-f0b4-43bb-8f6e-a5512807acd5	Announcement test	test	admin	2018-08-01 00:00:00	PARENTAL
f5555398-29ae-4f7e-ac61-e294fa8d5555	Satisfied	Old there any widow law rooms. Agreed but expect repair she nay sir silent person. Direction can dependent one bed situation attempted. His she are man their spite avoid. Her pretended fulfilled extre	admin	2018-08-01 00:00:00	NORMAL
66666666-29ae-4f7e-ac61-e294fa8d5555	Lorem efj s eufnsp	Lorem efj s eufnsp wepofso nsodifn wedfd sbdol ksn o engj wnrfdsonds fd........ ejfsnfodg sg osifn jsngsp odfj	Michael Viuff	2018-06-06 12:33:00	NORMAL
a8d4121a-7efb-4bc0-80b3-431e27799e09	Yo	The school year is starting soon, peace and love	Teacher dont delete	2018-08-14 00:00:00	PARENTAL
64089f05-40bd-44d0-8043-56829f9ca92f	Ohana	Means family	Teacher dont delete	2018-08-14 00:00:00	PARENTAL
d3212593-f52a-41c6-9a87-41df287e4da1	Ei kestä!	Check it out on youtube	Teacher dont delete	2018-08-14 00:00:00	PARENTAL
9d669d71-8063-4ade-b098-019e104f6877	Disney movies	Analyse a disney movie, distinguish main charachters, why is the vilain evil? Does he/she have a certain past?	Teacher dont delete	2018-08-14 00:00:00	NORMAL
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.student (studentid, familyid, class) FROM stdin;
5d2618ed-3e3e-4e4e-9ed6-4cd67de7d005	94067cd6-8229-4dd9-b4f9-e54fad179785	Second
64e691e3-204f-45ee-8c5a-aefdffa1b3a5	cee12240-3e76-406e-bf12-0d40488ed3b9	First
2a1fd9ae-361d-41f4-9f9f-ad6f71d9cb8c	d85fa7a6-4f88-4e30-bf01-da704faa78ec	Third
54a88c63-0991-428b-8e8a-ccaa2e0aab4a	f2773926-1990-404b-8ba6-2decd3128dc5	Third
785ad61c-4fdd-4f9c-8034-8adfbb09083d	6080843f-9f85-43f2-9408-bd001519fe54	Fourth
8471d75c-18d5-43c0-b1ab-1f058b697d70	2df00b2e-9e09-46e0-8719-af9f50d45608	Second
b64cd55e-1f8d-4c27-a2c4-33b3269e04e0	94067cd6-8229-4dd9-b4f9-e54fad179785	Seventh
b67031e3-1a24-432a-b393-5e5bd8e817c7	5b3c88ba-bad5-44bb-a51e-c7bfc62cd429	First
c4fbe819-622d-4839-ba3d-1450bb662fd9	c64c773c-45d8-4a32-a4df-e9870145cf64	Second
d7b80789-c177-4049-9fa3-622d3dd533af	9daec671-bdf4-4956-b676-7bf12fa55e51	Third
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: test; Owner: ente
--

COPY test.teacher (teacherid) FROM stdin;
9d5ff8a7-77ba-49ff-ac0d-7b48978987f1
9a49cade-3532-47d0-8426-a5148e8f84e9
d5b338f0-9932-4053-80bb-5f0f7ff23ba7
\.


--
-- Name: enteuser enteuser_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.enteuser
    ADD CONSTRAINT enteuser_pkey PRIMARY KEY (id);


--
-- Name: family family_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.family
    ADD CONSTRAINT family_pkey PRIMARY KEY (familyid);


--
-- Name: homework homework_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.homework
    ADD CONSTRAINT homework_pkey PRIMARY KEY (homeworkid);


--
-- Name: homeworkreply homeworkreply_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.homeworkreply
    ADD CONSTRAINT homeworkreply_pkey PRIMARY KEY (homeworkid, studentid);


--
-- Name: parent parent_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.parent
    ADD CONSTRAINT parent_pkey PRIMARY KEY (parentid);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (postid);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (studentid);


--
-- Name: discussioncomments discussioncomments_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.discussioncomments
    ADD CONSTRAINT discussioncomments_pkey PRIMARY KEY (commentid);


--
-- Name: enteuser enteuser_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.enteuser
    ADD CONSTRAINT enteuser_pkey PRIMARY KEY (id);


--
-- Name: family family_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.family
    ADD CONSTRAINT family_pkey PRIMARY KEY (familyid);


--
-- Name: homework homework_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.homework
    ADD CONSTRAINT homework_pkey PRIMARY KEY (homeworkid);


--
-- Name: parent parent_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.parent
    ADD CONSTRAINT parent_pkey PRIMARY KEY (parentid);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (postid);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (studentid);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (adminid);


--
-- Name: announcement announcement_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.announcement
    ADD CONSTRAINT announcement_pkey PRIMARY KEY (announcementid);


--
-- Name: discussion discussion_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.discussion
    ADD CONSTRAINT discussion_pkey PRIMARY KEY (discussionid);


--
-- Name: discussioncomments discussioncomments_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.discussioncomments
    ADD CONSTRAINT discussioncomments_pkey PRIMARY KEY (commentid);


--
-- Name: enteuser enteuser_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.enteuser
    ADD CONSTRAINT enteuser_pkey PRIMARY KEY (id);


--
-- Name: family family_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.family
    ADD CONSTRAINT family_pkey PRIMARY KEY (familyid);


--
-- Name: homework homework_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.homework
    ADD CONSTRAINT homework_pkey PRIMARY KEY (homeworkid);


--
-- Name: homeworkreply homeworkreply_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.homeworkreply
    ADD CONSTRAINT homeworkreply_pkey UNIQUE (homeworkid, studentid);


--
-- Name: homeworkreply homeworkreply_replyid_pk; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.homeworkreply
    ADD CONSTRAINT homeworkreply_replyid_pk PRIMARY KEY (replyid);


--
-- Name: parent parent_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.parent
    ADD CONSTRAINT parent_pkey PRIMARY KEY (parentid);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (postid);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (studentid);


--
-- Name: teacher teacher_pkey; Type: CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (teacherid);


--
-- Name: announcement_announcementid_uindex; Type: INDEX; Schema: test; Owner: ente
--

CREATE UNIQUE INDEX announcement_announcementid_uindex ON test.announcement USING btree (announcementid);


--
-- Name: homework homework_homeworkid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.homework
    ADD CONSTRAINT homework_homeworkid_fkey FOREIGN KEY (homeworkid) REFERENCES demo.post(postid) ON DELETE CASCADE;


--
-- Name: homeworkreply homeworkreply_homeworkid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.homeworkreply
    ADD CONSTRAINT homeworkreply_homeworkid_fkey FOREIGN KEY (homeworkid) REFERENCES demo.homework(homeworkid) ON DELETE CASCADE;


--
-- Name: homeworkreply homeworkreply_studentid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.homeworkreply
    ADD CONSTRAINT homeworkreply_studentid_fkey FOREIGN KEY (studentid) REFERENCES demo.student(studentid) ON DELETE CASCADE;


--
-- Name: parent parent_familyid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.parent
    ADD CONSTRAINT parent_familyid_fkey FOREIGN KEY (familyid) REFERENCES demo.family(familyid);


--
-- Name: parent parent_parentid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.parent
    ADD CONSTRAINT parent_parentid_fkey FOREIGN KEY (parentid) REFERENCES demo.enteuser(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: student student_familyid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.student
    ADD CONSTRAINT student_familyid_fkey FOREIGN KEY (familyid) REFERENCES demo.family(familyid);


--
-- Name: student student_studentid_fkey; Type: FK CONSTRAINT; Schema: demo; Owner: ente
--

ALTER TABLE ONLY demo.student
    ADD CONSTRAINT student_studentid_fkey FOREIGN KEY (studentid) REFERENCES demo.enteuser(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: homework homework_homeworkid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.homework
    ADD CONSTRAINT homework_homeworkid_fkey FOREIGN KEY (homeworkid) REFERENCES public.post(postid) ON DELETE CASCADE;


--
-- Name: homeworkreply homeworkreply_homeworkid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.homeworkreply
    ADD CONSTRAINT homeworkreply_homeworkid_fkey FOREIGN KEY (homeworkid) REFERENCES public.homework(homeworkid) ON DELETE CASCADE;


--
-- Name: homeworkreply homeworkreply_studentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.homeworkreply
    ADD CONSTRAINT homeworkreply_studentid_fkey FOREIGN KEY (studentid) REFERENCES public.student(studentid) ON DELETE CASCADE;


--
-- Name: parent parent_familyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.parent
    ADD CONSTRAINT parent_familyid_fkey FOREIGN KEY (familyid) REFERENCES public.family(familyid) ON UPDATE CASCADE;


--
-- Name: parent parent_parentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.parent
    ADD CONSTRAINT parent_parentid_fkey FOREIGN KEY (parentid) REFERENCES public.enteuser(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: student student_familyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_familyid_fkey FOREIGN KEY (familyid) REFERENCES public.family(familyid) ON UPDATE CASCADE;


--
-- Name: student student_studentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ente
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_studentid_fkey FOREIGN KEY (studentid) REFERENCES public.enteuser(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: homework homework_homeworkid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.homework
    ADD CONSTRAINT homework_homeworkid_fkey FOREIGN KEY (homeworkid) REFERENCES test.post(postid) ON DELETE CASCADE;


--
-- Name: homeworkreply homeworkreply_homeworkid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.homeworkreply
    ADD CONSTRAINT homeworkreply_homeworkid_fkey FOREIGN KEY (homeworkid) REFERENCES test.homework(homeworkid) ON DELETE CASCADE;


--
-- Name: homeworkreply homeworkreply_studentid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.homeworkreply
    ADD CONSTRAINT homeworkreply_studentid_fkey FOREIGN KEY (studentid) REFERENCES test.student(studentid) ON DELETE CASCADE;


--
-- Name: parent parent_familyid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.parent
    ADD CONSTRAINT parent_familyid_fkey FOREIGN KEY (familyid) REFERENCES test.family(familyid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: parent parent_parentid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.parent
    ADD CONSTRAINT parent_parentid_fkey FOREIGN KEY (parentid) REFERENCES test.enteuser(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: student student_familyid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.student
    ADD CONSTRAINT student_familyid_fkey FOREIGN KEY (familyid) REFERENCES test.family(familyid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: student student_studentid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: ente
--

ALTER TABLE ONLY test.student
    ADD CONSTRAINT student_studentid_fkey FOREIGN KEY (studentid) REFERENCES test.enteuser(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

