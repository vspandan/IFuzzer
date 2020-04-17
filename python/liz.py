from lizard import analyze_file
i = analyze_file.analyze_source_code("AllTests.cpp", "int foo(){}")

print i.__dict__
print i.token_count
for index in range(len(i.function_list)):
	print i.function_list[index].cyclomatic_complexity